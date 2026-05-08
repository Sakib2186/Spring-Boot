package com.eazybytes.jobportal.contact.controller.service.impl;

import com.eazybytes.jobportal.Dto.ContactRequestDto;
import com.eazybytes.jobportal.contact.controller.service.IContactService;
import com.eazybytes.jobportal.entity.Contact;
import com.eazybytes.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@RequiredArgsConstructor
@Primary
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean createContact(ContactRequestDto contactRequestDto) {
        boolean result = false;
        Contact contact = contactRepository.save(this.transferToEntity(contactRequestDto));
        if (contact != null && contact.getId()!=null){
            result=true;
        }
        return result;
    }

    private Contact transferToEntity(ContactRequestDto contactRequestDto){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto,contact); // source, destination to copy fields. ony for exact mathcing field name as entity and class
        // contact.setCreatedAt(Instant.now());
        // contact.setCreatedBy("System");
        // Now done by Auditing
        contact.setStatus("NEW");
        return contact;
    }


}
