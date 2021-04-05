package com.example.Planner.security;

import com.example.Planner.models.User;
import com.example.Planner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailure extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String message = "";
        String username = httpServletRequest.getParameter("txtUsername");

        User user = userRepository.findByUsername(username);

        if(!user.isActive()) {

            message = "User is not active!";

        } else {

            if(e.getClass() == UsernameNotFoundException.class) {
                message = "Cannot find a user";
            } else if(e.getClass() == BadCredentialsException.class) {
                message = "Check your password";
            }
        }

        httpServletResponse.sendRedirect(String.format("login?error&message=%s", message));

    }
}
