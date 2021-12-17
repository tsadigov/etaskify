package com.tsadigov.etaskify.service;


import com.tsadigov.etaskify.dto.UserDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser saveUser(AppUser user);

    Optional<AppUser> getUser(Long id);

    AppUser getUser(String username);

    AppUser findByUsername(String username);

    List<AppUser> getUsers();

    void addRoleToUser(String username, String roleName);

    Employee createUser(UserDTO userDTO);

}
