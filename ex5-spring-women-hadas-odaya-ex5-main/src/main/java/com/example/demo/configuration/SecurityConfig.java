package com.example.demo.configuration;

import com.example.demo.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * The SecurityConfig class configures the security settings for the application.
 * It includes authentication and authorization settings, as well as password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Constructs a SecurityConfig instance with the specified UserDetailsServiceImpl.
     *
     * @param userDetailsService the service for retrieving user details
     */
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain for the application.
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers(
                                "/",
                                "/home",
                                "/category/**",
                                "/cart/add",
                                "/cart/decrease",
                                "/cart/delete",
                                "/cart/delete/all",
                                "/signup", "/adduser", "/login",
                                "/imageBackground/**",
                                "/images/**",
                                "/css/**", "/js/**", "/index")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }

    /**
     * Provides a PasswordEncoder bean for encoding passwords.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication provider with the user details service and password encoder.
     *
     * @return a configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides an AuthenticationManager bean for managing authentication.
     *
     * @param authConfig the AuthenticationConfiguration object
     * @return an AuthenticationManager instance
     * @throws Exception if an error occurs while retrieving the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures the authentication success handler to redirect to the home page upon successful login.
     *
     * @return a configured AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/home");
        return successHandler;
    }
}
