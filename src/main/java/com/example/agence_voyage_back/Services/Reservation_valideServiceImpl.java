package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Annonce;
import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.Reservation;
import com.example.agence_voyage_back.Entities.Reservation_validee;
import com.example.agence_voyage_back.Repository.AnnonceRepository;
import com.example.agence_voyage_back.Repository.ClientRepository;
import com.example.agence_voyage_back.Repository.Reservation_valideeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Reservation_valideServiceImpl implements Reservation_valideService{

    @Autowired
    Reservation_valideeRepository reservationValideeRepository;
    @Autowired
    ClientRepository clientRepository;
  @Autowired
    AnnonceRepository annonceRepository;
    @Override
    public  ResponseEntity<?>addReservation(Reservation_validee reservation,Long id_client,Long id_annonce) {
        System.out.println("reservation "+reservation);
      Optional<Client> client= clientRepository.findById(id_client);

      Optional<Annonce>  annonce=annonceRepository.findById(id_annonce);
        System.out.println("client "+client);
        reservation.setAnnonce(annonce.get());
        reservation.setClient(client.get());
         reservationValideeRepository.save(reservation);
         System.out.println("reservation"+reservation);
             return new ResponseEntity<>(reservation,HttpStatus.OK);
    }

    @Override
    public List<Reservation_validee> getAllReservation() {
        return reservationValideeRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
       Optional<?> existReservation= reservationValideeRepository.findById(id);
         if (existReservation.isPresent()){
            return new ResponseEntity<>(existReservation, HttpStatus.OK);
         }
        return new ResponseEntity<>("pas de reservation",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> SuppReservation(Long id) {

            reservationValideeRepository.deleteById(id) ;
           return  ResponseEntity.ok().build();

    }

    @Override
    public String ModifReservation(Reservation_validee reservation) {
        return null;
    }
}
