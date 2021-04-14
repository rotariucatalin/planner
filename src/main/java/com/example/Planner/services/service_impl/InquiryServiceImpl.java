package com.example.Planner.services.service_impl;

import com.example.Planner.dto.InquiryDTO;
import com.example.Planner.models.Inquiry;
import com.example.Planner.repositories.InquiryRepository;
import com.example.Planner.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public Page<Inquiry> findAllByInqStatusStartingWith(int pageNo, int pageSize, String status) {

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize);
        if(status.equals("all"))        status      = "";

        return inquiryRepository.findAllByInqStatusStartingWith(pageable, status);
    }

    @Override
    public Inquiry findLastInq() {
        return inquiryRepository.findLastInquiry();
    }

    @Override
    public void saveInquiry(InquiryDTO inquiryDTO) {

        Inquiry inquiry = new Inquiry();
        inquiry.setInqNr(inquiryDTO.getInqNr());
        inquiry.setInqRevision(inquiryDTO.getInqRevision());
        inquiry.setInqDate(inquiryDTO.getInqDate());
        inquiry.setCompany(inquiryDTO.getCompany());
        inquiry.setContact(inquiryDTO.getContact());
        inquiry.setSalesCompany(inquiryDTO.getSalesCompany());
        inquiry.setSalesContact(inquiryDTO.getSalesContact());
        inquiry.setInqRate(inquiryDTO.getInqRate());
        inquiry.setInqPrice(inquiryDTO.getInqPrice());
        inquiry.setInqCurrency(inquiryDTO.getInqCurrency());
        inquiry.setInqProjectManager(inquiryDTO.getInqProjectManager());
        inquiry.setInqStatus(inquiryDTO.getInqStatus());
        inquiry.setInqPriority(inquiryDTO.getInqPriority());
        inquiry.setInqDescription(inquiryDTO.getInqDescription());

        inquiryRepository.save(inquiry);

    }

    @Override
    public Inquiry findById(int id) {
        return inquiryRepository.findById(id).get();
    }

    @Override
    public void updateInquiry(InquiryDTO inquiryDTO, int inquiryId) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId).get();
        inquiry.setInqNr(inquiryDTO.getInqNr());
        inquiry.setInqRevision(inquiryDTO.getInqRevision());
        inquiry.setInqDate(inquiryDTO.getInqDate());
        inquiry.setCompany(inquiryDTO.getCompany());
        inquiry.setContact(inquiryDTO.getContact());
        inquiry.setSalesCompany(inquiryDTO.getSalesCompany());
        inquiry.setSalesContact(inquiryDTO.getSalesContact());
        inquiry.setInqRate(inquiryDTO.getInqRate());
        inquiry.setInqPrice(inquiryDTO.getInqPrice());
        inquiry.setInqCurrency(inquiryDTO.getInqCurrency());
        inquiry.setInqProjectManager(inquiryDTO.getInqProjectManager());
        inquiry.setInqStatus(inquiryDTO.getInqStatus());
        inquiry.setInqPriority(inquiryDTO.getInqPriority());

        inquiryRepository.save(inquiry);
    }

    @Override
    public void deleteInquiry(int inquiryId) {
        inquiryRepository.deleteById(inquiryId);
    }

    @Override
    public List<Inquiry> findAllByCompanyId(int companyId) {
        return inquiryRepository.findAllByCompanyId(companyId);
    }
}
