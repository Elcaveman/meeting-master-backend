package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.dto.topic.SimpleTopicDTO;
import com.zsoft.meetingmasterbackend.models.Topic;
import com.zsoft.meetingmasterbackend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("")
    public ResponseEntity<List<SimpleTopicDTO>> getActions(@RequestParam(required = false) Long meetingId){
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getTopics());
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<SimpleTopicDTO>> getTopicsByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getTopicsByName(name));
    }
}
