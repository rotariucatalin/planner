package com.example.Planner.services.service_impl;

import com.example.Planner.dto.ContactDTO;
import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import com.example.Planner.repositories.ContactRepository;
import com.example.Planner.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAllContactsByCompanyID(int companyId) {

        return contactRepository.findContactByCompany_Id(companyId);
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
        contact.setCompany(contactDTO.getCompany());
        contact.setPosition(contactDTO.getPosition());
        contact.setDepartment(contactDTO.getDepartment());
        contact.setCountry(contactDTO.getCountry());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getEmail());
        contact.setStatus(contactDTO.getStatus());
        contact.setConsent(contactDTO.isConsent());

        return contact;
    }

    @Override
    @Transactional
    public void deleteContact(int contactId) {
        updateCompanySalesPerson(contactId);
        contactRepository.deleteById(contactId);
    }

    @Override
    public void updateCompanySalesPerson(int contactId) {
        contactRepository.updateCompanySalesPerson(contactId);
    }

    @Override
    public void saveContact(ContactDTO contactDTO) {

        Contact contact = new Contact();

        contact.setName(contactDTO.getName());
        contact.setPosition(contactDTO.getPosition());
        contact.setDepartment(contactDTO.getDepartment());
        contact.setCountry(contactDTO.getCountry());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setStatus(contactDTO.getStatus());
        contact.setConsent(contactDTO.isConsent());
        contact.setCompany(contactDTO.getCompany());

        contactRepository.save(contact);
    }

    @Override
    public List<Contact> excelExport() {
        return contactRepository.findAll();
    }
}
