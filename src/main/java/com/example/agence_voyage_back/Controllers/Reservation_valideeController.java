package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Reservation_validee;
import com.example.agence_voyage_back.Services.Reservation_valideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reservation")
public class Reservation_valideeController {
    @Autowired
    Reservation_valideService reservationValideService;
    @PostMapping("/{id}/{id_annonce}")
    public ResponseEntity<?> ajouterReservation(@RequestBody Reservation_validee reservationValidee, @PathVariable Long id,@PathVariable long id_annonce){

        System.out.println("reservation "+reservationValidee);
        return  reservationValideService.addReservation(reservationValidee,id,id_annonce);


    }
    @GetMapping()
    public List<?> getAll(){
        return reservationValideService.getAllReservation();
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> SuppReservation( @PathVariable Long id){
        return reservationValideService.SuppReservation(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>GetbyOne(@PathVariable Long id){
        return reservationValideService.getById(id);
    }

}
