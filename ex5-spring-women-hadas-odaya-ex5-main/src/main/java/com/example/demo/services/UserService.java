package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 * This class provides methods to save a new user, ensuring
 * that the username is unique and the password is securely
 * encoded before storing it in the database. It uses the
 * {@link UserRepository} to interact with the database and
 * {@link PasswordEncoder} to encode passwords.
 *
 * @see User
 * @see UserRepository
 * @see PasswordEncoder
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Saves a new user to the database.
     * <p>
     * This method checks if the username already exists in the
     * database. If it does, it throws an IllegalArgumentException.
     * Otherwise, it encodes the user's password and saves the user.
     *
     * @param user the user to be saved
     * @throws IllegalArgumentException if the username already exists
     */
    public void save(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUserName());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
