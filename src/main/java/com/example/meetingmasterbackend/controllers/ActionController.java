package com.example.meetingmasterbackend.controllers;

import com.example.meetingmasterbackend.models.Action;
import com.example.meetingmasterbackend.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/actions")
public class ActionController {
    private final ActionService actionService;
    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("")
    public ResponseEntity<List<Action>> getActions(){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Action> getActionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionById(id));
    }
    @GetMapping("/type/{id}")
    public ResponseEntity<List<Action>> getActionsByTypeId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByTypeId(id));
    }

//    @GetMapping("/byIds")
//    public ResponseEntity<List<Action>> getActionsById(@RequestParam("ids") Long[] ids){
//        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsById(ids));
//    }
}
