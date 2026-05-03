package com.eazybytes.jobportal.service;

import com.eazybytes.jobportal.Dto.CompanyDto;
import com.eazybytes.jobportal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();
}
