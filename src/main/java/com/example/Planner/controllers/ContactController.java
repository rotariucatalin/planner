package com.example.Planner.controllers;

import com.example.Planner.dto.ContactDTO;
import com.example.Planner.exception.RequestException;
import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import com.example.Planner.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        return findPaginated(1, model, itemsPerPage);

    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model,
                                @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        Page<Contact> page = contactService.findAll(pageNo, Integer.valueOf(itemsPerPage));

        List<Contact> contactList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("contacts", contactList);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "contact/index";
    }

    @GetMapping(value = "/editContact/{contactId}")
    public String editContact(Model model,
                              @PathVariable(value = "contactId") int contactId) {

        Contact contact = contactService.findById(contactId);
        model.addAttribute("contact", contact);

        return "/contact/edit_contact";
    }

    @PostMapping(value = "/updateContact/{contactId}")
    public String updateContact(ContactDTO contactDTO,
                                @PathVariable(value = "contactId") int contactId,
                                RedirectAttributes redirectAttributes) {

        try{

            contactService.updateContact(contactDTO, contactId);
            redirectAttributes.addFlashAttribute("message", "Contact updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to update this contact! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/contact/index";
    }

    @GetMapping(value = "/deleteContact/{contactId}")
    public String deleteContact(@PathVariable(value = "contactId") int contactId,
                                RedirectAttributes redirectAttributes) {

        try {

            contactService.deleteContact(contactId);
            redirectAttributes.addFlashAttribute("message", "Contact deleted successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to delete this contact! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/contact/index";
    }
}
