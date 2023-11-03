package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
    Optional findByTitre(String titre);
}
