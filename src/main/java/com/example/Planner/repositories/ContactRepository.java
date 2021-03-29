package com.example.Planner.repositories;

import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findContactByCompany_Id(int id);

    Page<Contact> findAll(Pageable pageable);

    @Modifying
    @Query(value = "update companies SET company_sales_person = null WHERE company_sales_person =:contact_id ", nativeQuery = true)
    void updateCompanySalesPerson(Integer contact_id);
}
