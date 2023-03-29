package com.zsoft.meetingmasterbackend.controllers;

import com.zsoft.meetingmasterbackend.dto.action.ActionCreateDTO;
import com.zsoft.meetingmasterbackend.dto.action.ActionUpdateDTO;
import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<SimpleActionDTO>> getActions(@RequestParam(required = false) Long typeId,@RequestParam(required = false) Long meetingId){
        if (meetingId!=null){
            return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByMeetingId(meetingId));
        }
        else if(typeId !=null)
            return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionsByTypeId(typeId));
        else return ResponseEntity.status(HttpStatus.OK).body(actionService.getActions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleActionDTO> getActionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(actionService.getActionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAction(@PathVariable Long id,@RequestBody ActionUpdateDTO actionUpdateDto){
        actionUpdateDto.setId(id);
        actionService.updateAction(actionUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SimpleActionDTO> createAction(@RequestBody ActionCreateDTO actionCreateDTO){
        final SimpleActionDTO result = actionService.createAction(actionCreateDTO);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(result.getId())
                                .toUri())
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAction(@PathVariable("id") Long id){
        actionService.deleteAction(id);
        return ResponseEntity.ok().build();
    }
}
