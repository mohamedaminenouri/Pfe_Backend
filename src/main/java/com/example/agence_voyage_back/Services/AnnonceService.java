package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Annonce;
import com.example.agence_voyage_back.Entities.AnnonceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnnonceService {
    ResponseEntity<Object> ajouterAnnonce(AnnonceDTO annonce);

    ResponseEntity<Object>modifierAnnonce(AnnonceDTO annonce,Long id);

    ResponseEntity<Object>supprimerAnnonce(Long id);

    ResponseEntity<Object> afficherAnnonce(Long id);
    List<Annonce> annonces();

}
