package com.example.meetingmasterbackend.services;

import com.example.meetingmasterbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    // TODO : Try without constructor, but with Autowired assigned to repo field
    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
