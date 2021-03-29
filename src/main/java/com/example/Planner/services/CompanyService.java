package com.example.Planner.services;

import com.example.Planner.dto.CompanyDTO;
import com.example.Planner.models.Company;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    @Cacheable(value = "allCompanies")
    List<Company> getAllCompanies();

    @Cacheable(value = "allCompaniesP")
    Page<Company> getAllCompaniesPaginate(int pageNo, int pageSize, String type);

    Company getCompany(int companyId);

    @Caching(evict = {
            @CacheEvict(value = "allCompaniesP", allEntries = true)
    })
    void saveCompany(CompanyDTO companyDTO);

    @Caching(evict = {
            @CacheEvict(value = "allCompaniesP", allEntries = true)
    })
    Company updateCompany(CompanyDTO companyDTO, int companyId);

    @Caching(evict = {
            @CacheEvict(value = "allCompaniesP", allEntries = true)
    })
    void deleteCompany(int companyId);


}
