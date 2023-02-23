package com.example.meetingmasterbackend.controllers;

import com.example.meetingmasterbackend.models.Meeting;
import com.example.meetingmasterbackend.models.Profile;
import com.example.meetingmasterbackend.services.ProfileService;
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
    public ResponseEntity<Profile> getProfilesById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileById(id));
    }
}
