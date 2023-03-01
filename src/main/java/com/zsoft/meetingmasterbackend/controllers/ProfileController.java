package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("")
    public ResponseEntity<List<Profile>> getProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfiles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileById(id));
    }
    @GetMapping(params = "email")
    public ResponseEntity<Profile> getProfileByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileByEmail(email));
    }
    @GetMapping(params = "name")
    public ResponseEntity<List<Profile>> getProfilesByNameContains(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfilesByNameContains(name));
    }
}
