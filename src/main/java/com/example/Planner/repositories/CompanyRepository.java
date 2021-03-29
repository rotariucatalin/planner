package com.example.Planner.repositories;

import com.example.Planner.models.Activity;
import com.example.Planner.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Page<Company> findAllByTypeContains(Pageable pageable, String type);

}
