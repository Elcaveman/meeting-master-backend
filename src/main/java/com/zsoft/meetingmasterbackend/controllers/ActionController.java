package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SimpleActionDTO>> getActions(){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleActionDTO> getActionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionById(id));
    }
    @GetMapping(params = "typeId")
    public ResponseEntity<List<SimpleActionDTO>> getActionsByTypeId(@RequestParam Long typeId){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByTypeId(typeId));
    }

//    @GetMapping(params = "byIds")
//    public ResponseEntity<List<ActionDTO>> getActionsById(@RequestParam("byIds") Long[] ids){
//        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsById(ids));
//    }
}
