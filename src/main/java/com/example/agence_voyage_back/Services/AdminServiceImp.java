package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Admin;
import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.ConfirmationToken;
import com.example.agence_voyage_back.Repository.AdminRepository;
import com.example.agence_voyage_back.Repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImp implements AdminService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AdminRepository adminRepository;
@Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
@Autowired
EmailService emailService;
    @Override
    public ResponseEntity<Object> AjouterAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findAdminByEmail(admin.getEmail());
        if (existingAdmin != null) {
            return new ResponseEntity<>("Email déjà existant", HttpStatus.valueOf(409));
        } else {
           admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            Admin savedAdmin = adminRepository.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Object> ModifierAdmin(Admin admin, Long id) {

        Admin oldAdmin =adminRepository.findById(id).get();
        if (oldAdmin !=null){
            oldAdmin.setNom(admin.getNom());
            oldAdmin.setEmail(admin.getEmail());
            oldAdmin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            oldAdmin.setPrenom(admin.getPrenom());
            adminRepository.save(oldAdmin);
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("admin n''existe pas", HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Object> SupprimerAdmin(Long id) {
             Optional<Admin> existAdmin=adminRepository.findById(id);
             if (existAdmin.isPresent()) {
                 adminRepository.delete(existAdmin.get());
                 return new ResponseEntity<>(existAdmin,HttpStatus.OK);
             }
             else {
                return  new ResponseEntity<>("non existe",HttpStatus.CONFLICT);
             }
    }

    @Override
    public List<Admin> AfficherAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> AfficherParId(Long id) {
        if (adminRepository.findById(id).isPresent()){
            return new ResponseEntity<>(adminRepository.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>("n''existe pas ",HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<?> resetPassword(String email) {
        Client client = adminRepository.findByEmail(email);
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

}
