package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingCreateDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<SimpleMeetingDTO>> getMeetings(){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleMeetingDTO> getMeetingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetingById(id));
    }

    @GetMapping(params = "typeId")
    public ResponseEntity<List<SimpleMeetingDTO>> getMeetingsByTypeId(@RequestParam Long typeId){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetingsByTypeId(typeId));
    }

    @PostMapping
    public ResponseEntity<MeetingCreateDTO> createMeeting(@RequestBody MeetingCreateDTO meetingCreateDTO){
        final MeetingCreateDTO result = meetingService.createMeeting(meetingCreateDTO);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(result.getId())
                                .toUri())
                .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingCreateDTO> updateMeeting(@PathVariable Long id, @RequestBody MeetingCreateDTO meetingCreateDTO){
        final MeetingCreateDTO result = meetingService.updateMeeting(id,meetingCreateDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeeting(@PathVariable("id") Long id){
        meetingService.deleteMeeting(id);
        return ResponseEntity.ok().build();
    }
}
