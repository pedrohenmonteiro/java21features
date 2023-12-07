package com.pedromonteiro.java21.application.repositories;

import com.pedromonteiro.java21.domain.User;

import java.util.Optional;
import java.util.Collection;

public interface UserRepository {

    User save(User user);

    Optional<User> userOfId(String userId);

    Optional<User> userOfEmail(String email);

    Collection<User> allUsers();
    
}
