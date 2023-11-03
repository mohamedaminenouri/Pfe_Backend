package com.example.agence_voyage_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String numTel;
    private String ville;
    @Enumerated(EnumType.STRING)
    private GenreClass genre;
    private String email;
    private String mdp;
    private boolean etat;
}
