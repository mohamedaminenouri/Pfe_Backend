package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.ConfirmationToken;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ResponseEntity<Object> ajouterClient(Client client);

    ResponseEntity<?> ModifierClient(Client client, Long id);


    ResponseEntity<Object> supprimerClient(Long id);

    List<Client> AfficherClients();

    ResponseEntity<Object> AfficherClient(Long id);
    ResponseEntity<?> confirmEmail(String confirmationToken);

boolean VerifMail(String email );


    ResponseEntity<?> resetPassword(String email);
    ResponseEntity<?>ChangePassword(Long id_client,Client client);

    List<Client> Recherche_nom_ou_prenom(String query);

}

