package com.example.meetingmasterbackend.repositories;

import com.example.meetingmasterbackend.models.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting,Long> {
}
