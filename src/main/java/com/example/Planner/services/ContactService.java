package com.example.Planner.services;

import com.example.Planner.dto.ContactDTO;
import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {

    List<Contact> findAllContactsByCompanyID(int companyId);

    @Cacheable(value = "allContacts")
    Page<Contact> findAll(int pageNo, int pageSize);

    Contact findById(int contactId);

    @Caching(evict = {
            @CacheEvict(value = "allContacts", allEntries = true)
    })
    Contact updateContact(ContactDTO contactDTO, int contactId);

    @Caching(evict = {
            @CacheEvict(value = "allContacts", allEntries = true)
    })
    void deleteContact(int contactId);

    void updateCompanySalesPerson(int contactId);

    void saveContact(ContactDTO contactDTO);

    List<Contact> findAll();

    List<Contact> excelExport();
}
