package com.example.Planner.services;

import com.example.Planner.dto.InquiryDTO;
import com.example.Planner.models.Inquiry;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InquiryService {

    Page<Inquiry> findAllByInqStatusStartingWith(int pageNo, int pageSize, String status);
    Inquiry findLastInq();
    void saveInquiry(InquiryDTO inquiryDTO);
    Inquiry findById(int id);
    void updateInquiry(InquiryDTO inquiryDTO, int inquiryId);
    void deleteInquiry(int inquiryId);
    List<Inquiry> findAllByCompanyId(int companyId);
}
