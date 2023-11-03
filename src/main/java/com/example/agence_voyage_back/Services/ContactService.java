package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Contact;

import java.util.List;

public interface ContactService {
    Contact AddContact(Contact contact);
    List<Contact> getContacts();
    void SupprimerContact(Long id);


}
