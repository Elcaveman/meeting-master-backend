package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.action.ActionDTO;
import com.zsoft.meetingmasterbackend.models.Action;
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
    public ResponseEntity<List<ActionDTO>> getActions(){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionDTO> getActionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionById(id));
    }
    @GetMapping("/type/{id}")
    public ResponseEntity<List<ActionDTO>> getActionsByTypeId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByTypeId(id));
    }

//    @GetMapping("/byIds")
//    public ResponseEntity<List<Action>> getActionsById(@RequestParam("ids") Long[] ids){
//        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsById(ids));
//    }
}
