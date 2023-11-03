package com.example.agence_voyage_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_deb ;
    private Date date_fin;
    private  int nb_personne;
    private int nb_chambre;
    private String pension;

}
