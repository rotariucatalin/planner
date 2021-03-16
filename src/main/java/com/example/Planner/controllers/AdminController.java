package com.example.Planner.controllers;

import com.example.Planner.dto.UserDTO;
import com.example.Planner.models.Permission;
import com.example.Planner.models.User;
import com.example.Planner.services.PermissionService;
import com.example.Planner.services.UserServicePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    static Logger log = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private UserServicePersonal userServicePersonal;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/index")
    public String adminPage(Model model) {
        return "admin/index";
    }

    @GetMapping("/users")
    public String allUsers(Model model,
                           @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {
        return paginate(1, model,itemsPerPage);
    }

    @GetMapping("/users/page/{pageNo}")
    public String paginate(@PathVariable(value = "pageNo") int pageNo,
                           Model model,
                           @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        Page<User> page = userServicePersonal.findAll(pageNo, Integer.valueOf(itemsPerPage));
        List<User> users = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", users);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "admin/users";
    }

    @GetMapping("/user/edit/{userId}")
    public String editUser(Model model,
                           @PathVariable(value = "userId") int userId) {

        User user = userServicePersonal.findUserById(userId);
        user.setPassword("");

        model.addAttribute("user", user);

        return "admin/edit_user";

    }

    @PostMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable(value = "userId") int userId,
                             UserDTO userDTO,
                             RedirectAttributes redirectAttributes) {

        try{

            userServicePersonal.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("message", "User updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") int userId,
                             RedirectAttributes redirectAttributes) {

        try{

            userServicePersonal.deleteUser(userId);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/user/authority/{userId}")
    public String authority(Model model,
                            @PathVariable(value = "userId") int userId) {

        User user = userServicePersonal.findUserById(userId);
        List<Permission> permissions = permissionService.findAll();

        model.addAttribute("user", user);
        permissions.forEach((permission) -> {
            model.addAttribute(permission.getName(), permission.getId());
        });

        return "admin/authority";
    }

}
