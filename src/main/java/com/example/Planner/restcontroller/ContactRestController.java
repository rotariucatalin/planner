package com.example.Planner.restcontroller;

import com.example.Planner.models.Contact;
import com.example.Planner.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactRestController {

    @Autowired
    ContactService contactService;


    @GetMapping("/contacts/contactsByCompanyId/{company_id}")
    public List<Contact> contactList(@PathVariable("company_id") int company_id) {

        return contactService.findAllContactsByCompanyID(company_id);
    }

}
