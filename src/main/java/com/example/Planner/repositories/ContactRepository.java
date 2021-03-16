package com.example.Planner.repositories;

import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findContactsByCompanySetId(int id);

    Page<Contact> findAll(Pageable pageable);

}
