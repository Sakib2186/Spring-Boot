package com.eazybytes.jobportal.contact.controller;

import com.eazybytes.jobportal.Dto.ContactRequestDto;
import com.eazybytes.jobportal.contact.controller.service.IContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-us")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0",path = "/create")
    public ResponseEntity<String> createContact(@RequestBody @Valid ContactRequestDto contactRequestDto){
        boolean created = contactService.createContact(contactRequestDto);
        if (created){
            return ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending contact request");

    }

    @GetMapping(version = "1.0",path="/")
    public ResponseEntity<String> fetchOpenContacts(@RequestParam @Validated
                                                    @NotBlank(message="Status is required")
                                                    @Size(min=4,message = "Status length should be of minimum 4 chars")
                                                    String status){
        return ResponseEntity.ok().body("Status: "+status);
    }
}
