package com.th3eng.vitalCert.config;

import com.th3eng.vitalCert.model.Employee;
import com.th3eng.vitalCert.repository.CitizenRepository;
import com.th3eng.vitalCert.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final EmployeeRepository userRepository;
    private  final CitizenRepository citizenRepository;

    @Bean
    public UserDetailsService userDetailsService(String test) {
        System.out.println("@@@@@@@ test = " + test);
        return username -> (UserDetails) userRepository.findBySsn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AuthenticationProvider authenticationProvider(String test) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(test));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
