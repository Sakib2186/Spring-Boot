package com.eazybytes.jobportal.company.controller;

import com.eazybytes.jobportal.Dto.CompanyDto;
import com.eazybytes.jobportal.company.controller.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor // lombok does the constructor we dont have to, and we can also overlook auto wired
public class CompanyController {

    private final ICompanyService companyService;

//    @Autowired
//    public CompanyController(@Qualifier("companysericeImpl") ICompanyService companyService){
//        this.companyService = companyService;
//    }

    @GetMapping(version = "1.0",path="/public")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        List<CompanyDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companies);
    }
}
