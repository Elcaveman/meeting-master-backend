package com.example.meetingmasterbackend.repositories;

import com.example.meetingmasterbackend.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile,Long> {
}
