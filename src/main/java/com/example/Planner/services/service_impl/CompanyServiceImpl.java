package com.example.Planner.services.service_impl;

import com.example.Planner.dto.CompanyDTO;
import com.example.Planner.models.Company;
import com.example.Planner.repositories.CompanyRepository;
import com.example.Planner.services.CompanyService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Cacheable(value = "allCompanies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(int companyId) {
        return companyRepository.findById(companyId).get();
    }

    @Override
    public Page<Company> getAllCompaniesPaginate(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return companyRepository.findAll(pageable);
    }

    @Override
    public void saveCompany(CompanyDTO companyDTO) {

        Company company = new Company();
        company.setName(companyDTO.getName());

        companyRepository.save(company);
    }

    @Override
    @Transactional
    public Company updateCompany(CompanyDTO companyDTO, int companyId) {

        Company company = companyRepository.findById(companyId).get();
        company.setName(companyDTO.getName());

        return company;
    }

    @Override
    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }
}