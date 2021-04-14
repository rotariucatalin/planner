package com.example.Planner.repositories;

import com.example.Planner.models.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {

    Page<Inquiry> findAllByInqStatusStartingWith(Pageable pageable, String status);
    @Query(value = "SELECT * from inquiries  inq ORDER BY inq.id desc limit 0,1", nativeQuery = true)
    Inquiry findLastInquiry();
    List<Inquiry> findAllByCompanyId(int companyId);
}
