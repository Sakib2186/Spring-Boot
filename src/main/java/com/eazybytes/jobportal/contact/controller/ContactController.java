package com.eazybytes.jobportal.contact.controller;

import com.eazybytes.jobportal.Dto.ContactRequestDto;
import com.eazybytes.jobportal.contact.controller.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-us")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0",path = "/create")
    public ResponseEntity<String> createContact(@RequestBody ContactRequestDto contactRequestDto){
        boolean created = contactService.createContact(contactRequestDto);
        if (created){
            return ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending contact request");

    }
}
