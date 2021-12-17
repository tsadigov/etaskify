package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.dto.UserDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.repository.EmployeeRepo;
import com.tsadigov.etaskify.repository.RoleRepo;
import com.tsadigov.etaskify.repository.AppUserRepo;
import com.tsadigov.etaskify.validator.StringValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.tsadigov.etaskify.config.Constants.SHOULD_NOT_BE_LESS_THAN_6_CHARACTER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final AppUserRepo userRepo;
    private final EmployeeRepo userDetailsRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Employee createUser(UserDTO userDTO) {

        String username = userDTO.getEmail().split("@")[0];

        AppUser user = new AppUser(
                null, username, passwordEncoder.encode(userDTO.getPassword()), new ArrayList<>());
        userRepo.save(user);
        log.info("Created user {}",userDTO);

        Role role = roleRepo.findRoleByRoleName("ROLE_EMPLOYEE");
        user.getRoles().add(role);

        Employee userDetails = new Employee(null, userDTO.getEmail(), userDTO.getName(), userDTO.getSurname(), user);
        userDetailsRepo.save(userDetails);

        return userDetails;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<AppUser> getUser(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new USER {} to the DB", user.getUsername());

        String password = user.getPassword();
        if (StringValidator.isValidPasswordFormat(password)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        else{
            throw new RuntimeException(SHOULD_NOT_BE_LESS_THAN_6_CHARACTER);
        }
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding rol {} to user {}", username, roleName);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findRoleByRoleName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
