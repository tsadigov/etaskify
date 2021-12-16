package com.tsadigov.etaskify.service;


import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Role;

import java.util.List;
import java.util.Optional;

public interface UserService{

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Optional<AppUser> getUser(Long id);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    AppUser findByUserName(String username);

}
