package com.example.agence_voyage_back.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class ReservationDTO {
    private Long id;
    private Date date_deb ;
    private Date date_fin;
    private  int nb_personne;
    private int nb_chambre;
    private String pension;
    @OneToOne(targetEntity = Client.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;
    public static Reservation ToEntity(ReservationDTO reservationDTO ){
        if (reservationDTO == null){
            return null;

        }
        Reservation reservation=new Reservation();
        reservation.setId(reservationDTO.getId());
       // reservation.setDate_deb(reservationDTO.getDate_deb());
       // reservation.setDate_fin(reservationDTO.getDate_fin());
        reservation.setNb_personne(reservationDTO.getNb_personne());
        reservation.setNb_chambre(reservationDTO.getNb_chambre());
        reservation.setPension(reservationDTO.getPension());

       return reservation;
    }
}
