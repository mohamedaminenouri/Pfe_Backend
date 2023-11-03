package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Admin;
import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.ConfirmationToken;
import com.example.agence_voyage_back.Repository.ClientRepository;
import com.example.agence_voyage_back.Repository.ConfirmationTokenRepository;
import com.example.agence_voyage_back.Services.ClientService;
import com.example.agence_voyage_back.Services.EmailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin("*")
@RestController
@RequestMapping("/client")

public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    EmailService emailService;

    @Autowired
ClientRepository clientRepository;

    @Autowired
    ConfirmationTokenRepository confirmationToken;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  /*  @PostMapping()
    public ResponseEntity<Object> AddClient(@RequestBody Client client) {
      //  client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));

               emailService.sendSimpleMessage(client.getEmail(), "Complete Registration","To confirm your account , please click here "+"http://localhost:8081/api/client/confirm_account/"+client.getEmail());
         return     clientService.ajouterClient(client);

    }*/
    /*  @GetMapping("/hashing/{psd}")
     public ResponseEntity<?>  hashPassword (@PathVariable String psd){
        Optional<?>ch= this.bCryptPasswordEncoder.encode(psd);
      }*/
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody Client client) {
      return clientService.ajouterClient(client);
  }
    @PostMapping()
    public ResponseEntity<?> ajouterClient(@RequestBody Client client) {
        Client existClient = clientRepository.findByEmail(client.getEmail());
                 if (existClient==null){
                     client.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
                     clientRepository.save(client);
                     return new ResponseEntity<>(client,HttpStatus.OK);

                 }
                 else {
                     return new ResponseEntity<>(client,HttpStatus.BAD_REQUEST);
                 }


    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return clientService.confirmEmail(confirmationToken);
    }

    @RequestMapping(value="/confirm-email", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confimerEmail(@RequestParam("token")String confirmationToken) {
        return clientService.resetPassword(confirmationToken);
    }


    @PutMapping("/modifier/{idclient}")
    public ResponseEntity<?> UpdateClient(@RequestBody Client newclient , @PathVariable(value="idclient") Long id){

        if(clientRepository.findById(id).isPresent()){
            Client client1=clientRepository.findById(id).get();
            client1.setId(id);
            client1.setNom(newclient.getNom());
            client1.setVille(newclient.getVille());
            client1.setNumTel(newclient.getNumTel());
            client1.setEmail(newclient.getEmail());
            client1.setPrenom(newclient.getPrenom());
            client1.setGenre(newclient.getGenre());
            // test si le mot de passe est crypte ou non

                   String premierCaractere= String.valueOf((client1.getMdp()).charAt(0));

            if (premierCaractere.equalsIgnoreCase("$"))
            {
                client1.setMdp(newclient.getMdp());
                System.out.println("mot de passe"+newclient.getMdp());
                System.out.println("premierCaractere"+premierCaractere);
            }
            else {
                System.out.println("mot de passe non crypte"+newclient.getMdp());
                System.out.println("premierCaractere"+premierCaractere);
                client1.setMdp(this.bCryptPasswordEncoder.encode(newclient.getMdp()));
            }




            clientRepository.save(client1);
            return new ResponseEntity<>(newclient,HttpStatus.OK);
        }


        return new ResponseEntity<>("echec",HttpStatus.BAD_REQUEST);




    }
      @PutMapping("/{idclient}")
    public ResponseEntity<?> ModifierClient(@RequestBody Client client , @PathVariable(value="idclient") Long id){

        if(clientRepository.findById(id).isPresent()){
            Client client1=clientRepository.findById(id).get();

            client1.setNom(client.getNom());
            client1.setVille(client.getVille());
            client1.setNumTel(client.getNumTel());
            client1.setEmail(client.getEmail());
            client1.setPrenom(client.getPrenom());
            client1.setGenre(client.getGenre());

          client1.setMdp(this.bCryptPasswordEncoder.encode(client.getMdp()));
             // client1.setMdp(client.getMdp());

             clientRepository.save(client1);
            return new ResponseEntity<>(client,HttpStatus.OK);
        }


          return new ResponseEntity<>("echec",HttpStatus.BAD_REQUEST);




    }
   /* @PutMapping("/confirm_account/{email}")
    public void findByEmail(@PathVariable String email){
        Client clientExist=clientRepository.findByEmail(email);
        clientExist.setEtat(true);
        clientRepository.save(clientExist);
        emailService.sendSimpleMessage(clientExist.getEmail(),"L'etat de votre compte","votre compte a été activer " );


    }*/
    @GetMapping("/resetPassword/{email}")
  public ResponseEntity<?> ResetPassword(@PathVariable String email){
      return clientService.resetPassword(email);
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  supprimerClient( @PathVariable Long id){
      return clientService.supprimerClient(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> afficher( @PathVariable Long id) {
        return clientService.AfficherClient(id);
    }


    @GetMapping()
    public List<Client> Users(){
        return  clientService.AfficherClients();
    }

    @PostMapping("/login")
// String , object : email , mot de passe
    public ResponseEntity<Map<String, Object>> loginClient(@RequestBody Client client) {
        System.out.println("in login-client" + client);
        HashMap<String, Object> response = new HashMap<>();

        Client userFromDB = clientRepository.findAdminByEmail(client.getEmail());
        System.out.println("userFromDB+client " + userFromDB);
        if (userFromDB == null) {
            response.put("message", "Client not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            // il crypte votre mot de passe (saisie) et il compare par qui il existe dans la base
            boolean compare = this.bCryptPasswordEncoder.matches(client.getMdp(), userFromDB.getMdp());

            System.out.println("compare" + compare);
            if (!compare) {
                response.put("message", "client not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }

@GetMapping("/recherche/{chaine}")
public List<Client> Recherche_nom_ou_prenom(@PathVariable String chaine){
        return clientService.Recherche_nom_ou_prenom(chaine);
}
@GetMapping("/verifMail/{email}")
public boolean verifMail(@PathVariable String email){
        return clientService.VerifMail(email);
}


}
