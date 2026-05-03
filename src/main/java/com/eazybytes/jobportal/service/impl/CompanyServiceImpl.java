package com.eazybytes.jobportal.service.impl;

import com.eazybytes.jobportal.Dto.CompanyDto;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.repository.CompanyRepository;
import com.eazybytes.jobportal.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("companysericeImpl")
@RequiredArgsConstructor
@Primary
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

//    @Autowired
//    public CompanyServiceImpl(CompanyRepository companyRepository){
//        this.companyRepository = companyRepository;
//    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(this::transfetToDto).collect(Collectors.toList());
    }

    private CompanyDto transfetToDto(Company company){
        return new CompanyDto(company.getId(),company.getName(),company.getLogo(),
                company.getIndustry(),company.getSize(),company.getRating(),company.getLocations(),
                company.getFounded(),company.getDescription(),company.getEmployees(),
                company.getWebsite(),company.getCreatedAt());

    }
}
