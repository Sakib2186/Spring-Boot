package com.eazybytes.jobportal.contact.controller.service;

import com.eazybytes.jobportal.Dto.ContactRequestDto;

public interface IContactService {

    boolean createContact(ContactRequestDto contactRequestDto);
}
