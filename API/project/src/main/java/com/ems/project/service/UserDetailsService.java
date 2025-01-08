package com.ems.project.service;

import com.ems.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }

}
