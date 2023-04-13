package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.mappers.ProfileMapper;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    // TODO : Try without constructor, but with Autowired assigned to repo field
    @Autowired
    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public List<ProfileDTO> getProfiles(){
        return this.profileRepository.findAll().stream().map(profileMapper::toProfileDto).collect(Collectors.toList());
    }
    public ProfileDTO getProfileById(Long id){
        return profileMapper.toProfileDto(this.profileRepository.findProfileById(id));
    }
    public ProfileDTO getProfileByEmail(String email){
        return profileMapper.toProfileDto(this.profileRepository.findProfileByEmailIgnoreCase(email));
    }
    public List<ProfileDTO> getProfilesByNameContains(String name){
        return this.profileRepository
                .findProfilesByNameContainsIgnoreCase(name)
                .stream()
                .map(profileMapper::toProfileDto)
                .collect(Collectors.toList());
    }

    public List<ProfileDTO> getProfilesByNameOrEmailContains(String nameOrEmail){
        return this.profileRepository
                .findDistinctTop3ByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(nameOrEmail,nameOrEmail)
                .stream()
                .map(profileMapper::toProfileDto)
                .collect(Collectors.toList());
    }
}
