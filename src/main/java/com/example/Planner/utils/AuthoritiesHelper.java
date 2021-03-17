package com.example.Planner.utils;

import com.example.Planner.models.User;
import com.example.Planner.repositories.UserRepository;
import com.example.Planner.services.service_impl.UserServicePersonalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthoritiesHelper {

    public void updateAuthorities(User user) {

        List<GrantedAuthority> actualAuthorities = user.getPermissions().stream().map(userRole -> new SimpleGrantedAuthority(userRole.getName())).collect(Collectors.toList());
        //Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), actualAuthorities);
        Authentication at2 = new PreAuthenticatedAuthenticationToken(user.getUsername(), user.getPassword(), actualAuthorities);
        SecurityContextHolder.getContext().setAuthentication(at2);

    }

}
