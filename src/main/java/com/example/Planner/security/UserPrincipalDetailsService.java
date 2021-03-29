package com.example.Planner.security;

import com.example.Planner.models.User;
import com.example.Planner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user.isActive()) {

            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;

        } else {

            throw new UsernameNotFoundException("Username is not active!");

        }
    }

}
