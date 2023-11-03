package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Categorie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategorieService {
    ResponseEntity<Object>ajouterCategorie(Categorie categorie);
    ResponseEntity<Object>ModifierCategorie(Categorie categorie,Long id);
    ResponseEntity<Object>AfficherCategorie(Long id);
    ResponseEntity<Object> SupprimerCategorie(Long id);



    List<Categorie> Categories();
}
