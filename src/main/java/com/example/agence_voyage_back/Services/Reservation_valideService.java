package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.Reservation_validee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Reservation_valideService {
    ResponseEntity<?> addReservation(Reservation_validee reservation, Long client,Long annonce);
    List<Reservation_validee> getAllReservation();

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> SuppReservation (Long id);
    String ModifReservation(Reservation_validee reservation);

}
