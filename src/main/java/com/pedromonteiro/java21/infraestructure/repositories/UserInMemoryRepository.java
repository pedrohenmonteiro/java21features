package com.pedromonteiro.java21.infraestructure.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pedromonteiro.java21.application.repositories.UserRepository;
import com.pedromonteiro.java21.domain.User;

@Component
public class UserInMemoryRepository implements UserRepository {

    private final Map<String, User> table;

    private final Map<String, User> emailIndex;

    

    public UserInMemoryRepository() {
        this.table = new ConcurrentHashMap<>();
        this.emailIndex = new ConcurrentHashMap<>();
    }

    @Override
    public User save(User user) {
        this.table.put(user.id(), user);
        this.emailIndex.put(user.email(), user);
        return user;
    }

    @Override
    public Optional<User> userOfId(String userId) {
        return Optional.ofNullable(this.table.get(userId));
    }

    @Override
    public Optional<User> userOfEmail(String email) {
        return Optional.ofNullable(this.emailIndex.get(email));

    }

    @Override
    public Collection<User> allUsers() {
        return this.table.values();
    }
    
}
