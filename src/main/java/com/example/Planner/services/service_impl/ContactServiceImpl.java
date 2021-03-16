package com.example.Planner.services.service_impl;

import com.example.Planner.dto.ContactDTO;
import com.example.Planner.models.Contact;
import com.example.Planner.repositories.ContactRepository;
import com.example.Planner.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAllContactsByCompanyID(int companyId) {

        return contactRepository.findContactsByCompanySetId(companyId);
    }

    @Override
    public Page<Contact> findAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact findById(int contactId) {
        return contactRepository.findById(contactId).get();
    }

    @Override
    @Transactional
    public Contact updateContact(ContactDTO contactDTO, int contactId) {

        Contact contact = contactRepository.findById(contactId).get();
        contact.setName(contactDTO.getName());

        return contact;
    }

    @Override
    public void deleteContact(int contactId) {
        contactRepository.deleteById(contactId);
    }
}
