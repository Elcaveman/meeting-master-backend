package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    List<Profile> findAll();

    List<Profile> findProfilesById(Long[] ids);
    Profile findProfileById(Long id);

    Profile findProfileByIdOrEmail(Long id, String email);
    Profile findProfileByEmailIgnoreCase(String email);
    List<Profile> findProfilesByNameContainsIgnoreCase(String name);

    List<Profile> findDistinctTop3ByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}
