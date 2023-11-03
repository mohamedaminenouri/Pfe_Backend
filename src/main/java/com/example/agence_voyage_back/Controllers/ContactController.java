package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Contact;
import com.example.agence_voyage_back.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactController  {
    @Autowired
    ContactService contactService;
    @PostMapping()
    public Contact ajouterContact(@RequestBody Contact contact){
        return contactService.AddContact(contact);
    }
    @GetMapping()
    public List<Contact> getContacts(){
        return contactService.getContacts();
    }

    @DeleteMapping("/{id}")
    public void  SupprimerContact(@PathVariable Long id){
        contactService.SupprimerContact(id);
    }
}
