package com.tsadigov.etaskify.service;

import com.tsadigov.etaskify.config.Mapper;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.dto.UserCreationDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.domain.Role;
import com.tsadigov.etaskify.dto.UserDTO;
import com.tsadigov.etaskify.exception.AlreadyExistException;
import com.tsadigov.etaskify.exception.ResourceNotFoundException;
import com.tsadigov.etaskify.repository.EmployeeRepo;
import com.tsadigov.etaskify.repository.OrganizationRepo;
import com.tsadigov.etaskify.repository.RoleRepo;
import com.tsadigov.etaskify.repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

import static com.tsadigov.etaskify.bootstap.Constants.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final AppUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepo employeeRepo;
    private final OrganizationRepo organizationRepo;

    @Override
    @Transactional
    public Employee createUser(UserCreationDTO userCreationDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Organization organization = userRepo.findByUsername(auth.getPrincipal().toString()).getOrganization();

        String username = userCreationDTO.getEmail().split("@")[0];

        AppUser user = new AppUser(
                null, username, passwordEncoder.encode(userCreationDTO.getPassword()), new ArrayList<>(), organization);
        userRepo.save(user);
        log.info("Created user {}", userCreationDTO);

        Role role = roleRepo.findRoleByRoleName("ROLE_EMPLOYEE");
        user.getRoles().add(role);

        Employee employee = new Employee(null, userCreationDTO.getEmail(), userCreationDTO.getName(), userCreationDTO.getSurname(), user);
        employeeRepo.save(employee);

        return employee;
    }

    @Override
    @Transactional
    public void signUp(SignUpDTO signUpDTO) {

        if (findByUsername(signUpDTO.getUsername()) != null)
            throw new AlreadyExistException(ALREADY_EXISTS);

        // add organization
        Organization organization = new Organization();
        organization.setOrganizationName(signUpDTO.getOrganizationName());
        organization.setPhoneNumber(signUpDTO.getPhoneNumber());
        organization.setAddress(signUpDTO.getAddress());
        organization.setStatus(true);
        organizationRepo.save(organization);

        // add admin user
        AppUser user = new AppUser();
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setOrganization(organization);
        userRepo.save(user);

        // get admin role
        Role role = roleRepo.findRoleByRoleName(ROLE_ADMIN);
        addRoleToUser(user.getUsername(), role.getRoleName());


        // save employee
        Employee employee = new Employee();
        employee.setEmail(signUpDTO.getEmail());
        employee.setUserFk(user);
        employeeRepo.save(employee);
    }

    @Override
    public String getUserEmailByUsername(String username) {
        String email;
        try{
            AppUser user = findByUsername(username);
            Employee employee = employeeRepo.getById(user.getId());
            email = employee.getEmail();
        }
        catch (Exception ex){
            log.info("Username {} not found in db", username);
            throw new ResourceNotFoundException(username+" : "+USER_NOT_FOUND);
        }
        return email;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found in the database");
            throw new ResourceNotFoundException(USER_NOT_FOUND);
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
    public UserDTO getUser(Long id) {
        Optional<AppUser> user = userRepo.findById(id);
        log.info("Fetching user {}", user.get().getUsername());
        UserDTO userDTO = Mapper.map(user.get(), UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("Fetching all users");
        List<AppUser> users = userRepo.findAll();
        List<UserDTO> userDTOS = Mapper.mapAll(users, UserDTO.class);

        return userDTOS;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new USER {} to the DB", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding rol {} to user {}", username, roleName);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findRoleByRoleName(roleName);

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
