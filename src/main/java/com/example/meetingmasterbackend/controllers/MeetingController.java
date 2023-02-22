package com.example.meetingmasterbackend.controllers;

import com.example.meetingmasterbackend.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    private final MeetingService meetingService;
    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
}
