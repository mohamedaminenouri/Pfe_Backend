package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Contact;
import com.example.agence_voyage_back.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService {

   @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact AddContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void SupprimerContact(Long id) {
         contactRepository.deleteById(id);
    }
}
