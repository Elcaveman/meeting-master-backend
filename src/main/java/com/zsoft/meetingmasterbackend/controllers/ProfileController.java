package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
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
    public ResponseEntity<List<ProfileDTO>> getProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfiles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileById(id));
    }
    @PostMapping("/search-email")
    public ResponseEntity<ProfileDTO> getProfileByEmail(@RequestParam("email") String email){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileByEmail(email));
    }
    @GetMapping(params = "name")
    public ResponseEntity<List<ProfileDTO>> getProfilesByNameContains(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfilesByNameContains(name));
    }

    @GetMapping(params = "nameOrEmail")
    public ResponseEntity<List<ProfileDTO>> getProfilesByNameOrEmailContains(@RequestParam String nameOrEmail){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfilesByNameOrEmailContains(nameOrEmail));
    }
}