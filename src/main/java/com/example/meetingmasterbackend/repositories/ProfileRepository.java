package com.example.meetingmasterbackend.repositories;

import com.example.meetingmasterbackend.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile,Long> {
    List<Profile> findAll();

    List<Profile> findProfilesById(long[] ids);
    Profile findProfileById(long id);
    Profile findProfileByEmail(String email);
    List<Profile> findProfilesByNameContains(String name);
}
