package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile,Long> {
    List<Profile> findAll();

    List<Profile> findProfilesById(Long[] ids);
    Profile findProfileById(Long id);
    Profile findProfileByEmailIgnoreCase(String email);
    List<Profile> findProfilesByNameContainsIgnoreCase(String name);
}
