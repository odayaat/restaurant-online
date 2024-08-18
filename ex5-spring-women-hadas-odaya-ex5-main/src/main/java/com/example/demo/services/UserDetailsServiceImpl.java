package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * The UserDetailsServiceImpl class implements the UserDetailsService interface
 * to provide custom user authentication logic.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by username and returns UserDetails for authentication.
     *
     * @param username the username of the user to be loaded
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(Collections.singleton(authority))
                .build();
    }
}
