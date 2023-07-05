package com.success.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.success.repository.UserSFARepository;


@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {
    private UserSFARepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new UserRegistrationDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}