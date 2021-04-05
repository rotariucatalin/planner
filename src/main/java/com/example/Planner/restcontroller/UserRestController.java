package com.example.Planner.restcontroller;

import com.example.Planner.models.Permission;
import com.example.Planner.models.User;
import com.example.Planner.pdf.InquiryPDF;
import com.example.Planner.services.UserServicePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServicePersonal userServicePersonal;

    @Autowired
    private InquiryPDF inquiryPDF;

    @GetMapping(value = "/admin/user/getAuthorityList/{userId}")
    public List<Permission> getAuthorityList(@PathVariable(value = "userId") int userId) {

        return userServicePersonal.findUserById(userId).getPermissions();

    }

    @PostMapping(value = "/admin/user/updateAuthority/{userId}", produces = "application/json")
    public void updateAuthority(@PathVariable(value = "userId") int userId,
                                @RequestBody String permissionJson) {

        User user = userServicePersonal.findUserById(userId);

        userServicePersonal.updateUser(user, permissionJson);
    }

    @GetMapping(value = "/generateInquiry")
    public ResponseEntity<?> createPDF() {

        return inquiryPDF.createPDF();
    }

}
