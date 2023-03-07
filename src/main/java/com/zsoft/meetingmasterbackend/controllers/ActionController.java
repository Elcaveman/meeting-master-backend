package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.action.ActionDTO;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.services.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/actions")
public class ActionController {
    private static final Logger log = LoggerFactory.getLogger(ActionController.class);
    private final ActionService actionService;
    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("")
    public ResponseEntity<List<ActionDTO>> getActions(){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionDTO> getActionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionById(id));
    }
    @GetMapping(params = "typeId")
    public ResponseEntity<List<ActionDTO>> getActionsByTypeId(@RequestParam Long typeId){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByTypeId(typeId));
    }

//    @GetMapping(params = "ids")
//    public ResponseEntity<List<ActionDTO>> getActionsById(@RequestParam("ids") Long[] ids){
//        System.out.println("Diaeddin APP :::");
//        Arrays.stream(ids).toList().forEach(System.out::print);
//        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsById(ids));
//    }
}
