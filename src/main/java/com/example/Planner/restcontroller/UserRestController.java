package com.example.Planner.restcontroller;

import com.example.Planner.models.Permission;
import com.example.Planner.models.User;
import com.example.Planner.services.UserServicePersonal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServicePersonal userServicePersonal;

    @GetMapping(value = "/admin/user/getAuthorityList/{userId}")
    public List<Permission> getAuthorityList(@PathVariable(value = "userId") int userId) {

        String tesdt = "1231231";

        return userServicePersonal.findUserById(userId).getPermissions();

    }

    @PostMapping(value = "/admin/user/updateAuthority/{userId}", produces = "application/json")
    public void updateAuthority(@PathVariable(value = "userId") int userId,
                                @RequestBody String permissionJson) {

        User user = userServicePersonal.findUserById(userId);
        List<Permission> newPermissionList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        try {

            Permission[] jsonObj = mapper.readValue(permissionJson, Permission[].class);
            for (Permission permission : jsonObj) {
                if(permission.getId() != 0)
                newPermissionList.add(permission);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        user.setPermissions(newPermissionList);

        userServicePersonal.updateUser(user);
    }
}
