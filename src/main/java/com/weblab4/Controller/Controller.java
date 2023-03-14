package com.weblab4.Controller;

import com.weblab4.Model.AuthEntry;
import com.weblab4.Model.HitEntry;
import com.weblab4.Others.Encryptor;
import com.weblab4.Repository.AuthRepository;
import com.weblab4.Repository.HitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Transactional
@CrossOrigin
@RestController
@RequestMapping("/main")
public class Controller {

    @Autowired
    HitRepository hitRepository;

    @Autowired
    AuthRepository authRepository;

    @GetMapping("/hits/{login}")
    public ResponseEntity<List<HitEntry>> getAllHitEntriesByLogin(@PathVariable("login") String login){
        try {

            List<HitEntry> entries = new ArrayList<>(hitRepository.findByLogin(login));

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println(entries.size());

            return new ResponseEntity<>(entries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthEntry> authUser(@RequestBody AuthEntry entry){
        System.out.println("log " + entry.getLogin() + "pass " + entry.getPassword());
        AuthEntry dbEntry = authRepository.findByLogin(entry.getLogin());
        if(dbEntry !=null && dbEntry.getPassword().equals(Encryptor.getSHA512Encode(entry.getPassword()))){
            System.out.println("User founded!");
//            entry.setPassword("testPassed");
            return new ResponseEntity<>(entry,HttpStatus.OK);
        }else{
            System.out.println("User not found");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/registration")
    public ResponseEntity<AuthEntry> registerUser(@RequestBody AuthEntry entry){
        String pass = entry.getPassword();
        entry.setPassword(Encryptor.getSHA512Encode(pass));

        if(authRepository.findByLogin(entry.getLogin()) == null) {

            authRepository.save(entry);
            entry.setPassword(pass);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }else {
            System.out.println("User already exist");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/hits/add")
    public ResponseEntity<HitEntry> addEmployee(@RequestBody HitEntry hit) {
        hit.checkHit();
        HitEntry newHit = hitRepository.save(hit);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("login") String login) {
        hitRepository.deleteHitEntriesByLogin(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/allHits/{login}")
//    public ResponseEntity<List<HitEntry>> getAllHitEntriesByLogin(@PathVariable("login") String login) {
//        try {
//
//            List<HitEntry> entries = new ArrayList<>(repository.findByLoginContaining(login));
//
//            if (entries.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(entries, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
