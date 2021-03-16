package com.example.Planner.repositories;

import com.example.Planner.models.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Page<Activity> findByStatusStartingWith(Pageable pageable, String status);

    Page<Activity> findByStatusStartingWithAndCompany_NameStartingWith(Pageable pageable, String status, String companyName);

    Page<Activity> findByStatusStartingWithAndCompany_NameStartingWithAndContact_Id(Pageable pageable, String status, String companyName, int contactId);

    List<Activity> findByStatusStartingWithAndCompany_NameStartingWithAndContact_Id(String status, String companyName, int contactId);
    List<Activity> findByStatusStartingWithAndCompany_NameStartingWith(String status, String companyName);
}
