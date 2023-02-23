package com.example.meetingmasterbackend.services;

import com.example.meetingmasterbackend.models.Meeting;
import com.example.meetingmasterbackend.repositories.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<Meeting> getMeetings(){
        return this.meetingRepository.findAll();
    }

    public Meeting getMeetingById(Long id){
        return this.meetingRepository.findMeetingById(id);
    }

    public List<Meeting> getMeetingsByTypeId(Long id){
        return this.meetingRepository.findMeetingsByTypeId(id);
    }
}
