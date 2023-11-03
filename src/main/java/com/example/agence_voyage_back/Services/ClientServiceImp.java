package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.ConfirmationToken;
import com.example.agence_voyage_back.Repository.ClientRepository;
import com.example.agence_voyage_back.Repository.ConfirmationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService{


    @Autowired
    ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailService emailService;
    @Override
    public ResponseEntity<Object> ajouterClient(Client client) {
        Client existingUser = clientRepository.findByEmail(client.getEmail());
        if (existingUser!=null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        //  client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
       client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
        clientRepository.save(client);
        ConfirmationToken confirmationToken = new ConfirmationToken(client);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(client.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/api/client/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }
   @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {

            Client client = clientRepository.findByEmail(token.getClient().getEmail());
            System.out.println("email from token " +token.getClient().getEmail());
            client.setEtat(true);
            clientRepository.save(client);
            return ResponseEntity.ok("Email verified successfully! "+"http://localhost:4200/login"  );
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public boolean VerifMail(String email) {
               if (clientRepository.existsByEmail(email))  {
                   return true;
               }
               else {
                   return false;
               }

            }


    @Override
    public ResponseEntity<?> resetPassword(String email) {
        Client client = clientRepository.findByEmail(email);
        if(client != null)
        {
            ConfirmationToken confirmationToken = new ConfirmationToken(client);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(client.getEmail());
            mailMessage.setSubject("Complete Validation!");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:4200/changePassword/"+client.getId());
            emailService.sendEmail(mailMessage);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }

            return new ResponseEntity<>("fail : email not verify",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<?> ChangePassword(Long  id, Client client) {

       return null;
    }

    @Override
    public List<Client> Recherche_nom_ou_prenom(String query) {
        return clientRepository.findByNomContainingOrPrenomContaining(query, query);

    }



    @Override
    public     ResponseEntity<?>   ModifierClient(Client client, Long id) {
        Client existClient =clientRepository.findById(id).get();
        if (existClient !=null) {
        System.out.println(" //////////////////exist Client"+existClient);
        System.out.println("///////////////////client new "+client);
            existClient.setEmail(client.getEmail());
            existClient.setNom(client.getNom());
            existClient.setPrenom(client.getPrenom());
            existClient.setNumTel(client.getNumTel());
            existClient.setVille(client.getVille());
            existClient.setGenre(client.getGenre());
           existClient.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
            existClient.setMdp(client.getMdp());
            clientRepository.save(existClient);
            return new ResponseEntity<>(client,HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("echec ",HttpStatus.CONFLICT);

    }
    @Transactional
    @Override
    public ResponseEntity<Object>supprimerClient(Long id){
        clientRepository.findById(id);
      //  confirmationTokenRepository.deleteByClient(clientRepository.findById(id).get());
        Optional<Client> existClient=clientRepository.findById(id);
        if (existClient.isPresent()){
            clientRepository.deleteById(id);
            return new ResponseEntity<>(existClient,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("client n''existe pas",HttpStatus.CONFLICT);
        }

    }


    @Override
    public List<Client> AfficherClients() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> AfficherClient(Long id) {

       Optional<Client> client=  clientRepository.findById(id);
       if (client.isPresent()) {
            return new ResponseEntity<>(client.get(),HttpStatus.OK);
        }
         else {
             return new ResponseEntity<>("client ne trouver pas",HttpStatus.NOT_FOUND);
       }
    }





}
