package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Admin;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    ResponseEntity<Object> AjouterAdmin(Admin admin);
    ResponseEntity<Object>  ModifierAdmin(Admin admin , Long id );

    ResponseEntity<Object> SupprimerAdmin (Long id);

    List<Admin> AfficherAdmin();

    ResponseEntity<Object> AfficherParId(Long id);

    ResponseEntity<?> resetPassword(String email);
}
