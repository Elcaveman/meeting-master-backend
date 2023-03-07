package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.services.MeetingService;
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
    public ResponseEntity<List<MeetingDTO>> getMeetings(){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> getMeetingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetingById(id));
    }

    @GetMapping(params = "typeId")
    public ResponseEntity<List<MeetingDTO>> getMeetingsByTypeId(@RequestParam Long typeId){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetingsByTypeId(typeId));
    }
}
