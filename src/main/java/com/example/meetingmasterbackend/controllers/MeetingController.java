package com.example.meetingmasterbackend.controllers;

import com.example.meetingmasterbackend.models.Action;
import com.example.meetingmasterbackend.models.Meeting;
import com.example.meetingmasterbackend.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    private final MeetingService meetingService;
    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    @GetMapping("")
    public ResponseEntity<List<Meeting>> getMeetings(){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetingById(id));
    }
}
