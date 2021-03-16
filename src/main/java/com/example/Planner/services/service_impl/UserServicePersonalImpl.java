package com.example.Planner.services.service_impl;

import com.example.Planner.dto.UserDTO;
import com.example.Planner.models.Permission;
import com.example.Planner.models.User;
import com.example.Planner.repositories.UserRepository;
import com.example.Planner.services.UserServicePersonal;
import com.example.Planner.utils.AuthoritiesHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicePersonalImpl implements UserServicePersonal {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthoritiesHelper authoritiesHelper;

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {

        User user = userRepository.findById(userDTO.getId()).get();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if(userDTO.getPassword().length() != 0)
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize);

        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(User user, String permissions) {


        List<Permission> newPermissionList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {

            Permission[] jsonObj = mapper.readValue(permissions, Permission[].class);
            for (Permission permission : jsonObj) {
                if(permission.getId() != 0)
                    newPermissionList.add(permission);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        user.setPermissions(newPermissionList);

        authoritiesHelper.updateAuthorities(user);

        userRepository.save(user);
    }
}
