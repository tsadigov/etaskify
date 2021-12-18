package com.tsadigov.etaskify.service;


import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.dto.UserCreationDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser saveUser(AppUser user);

    UserDTO getUser(Long id);

    AppUser getUser(String username);

    AppUser findByUsername(String username);

    List<UserDTO> getUsers();

    void addRoleToUser(String username, String roleName);

    Employee createUser(UserCreationDTO userCreationDTO);

    void signUp(SignUpDTO signUpDTO);

    String getUserEmailByUsername(String username);

}
