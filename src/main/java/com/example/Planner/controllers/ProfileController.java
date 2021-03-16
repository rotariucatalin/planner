package com.example.Planner.controllers;

import com.example.Planner.dto.UserDTO;
import com.example.Planner.models.User;
import com.example.Planner.security.UserPrincipal;
import com.example.Planner.services.UserServicePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UserServicePersonal userServicePersonal;

    @GetMapping("index")
    public String index(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal currentUser = (UserPrincipal)auth.getPrincipal();

        User user = userServicePersonal.findUserByUsername(currentUser.getUsername());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        model.addAttribute("user", userDTO);

        return "profile/index";
    }

    @PostMapping("updateProfile/{userId}")
    public String updateProfile(@PathVariable(value = "userId") int userId, UserDTO userDTO) {

        userServicePersonal.updateUser(userDTO);

        SecurityContextHolder.getContext().setAuthentication(null);

        return "login";

    }

}
