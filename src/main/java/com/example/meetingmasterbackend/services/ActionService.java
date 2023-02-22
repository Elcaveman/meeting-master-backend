package com.example.meetingmasterbackend.services;

import com.example.meetingmasterbackend.models.Action;
import com.example.meetingmasterbackend.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    private final ActionRepository actionRepository;
    @Autowired
    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> getActions(){return actionRepository.findAll();}
    public Action getActionById(long id){return actionRepository.findActionById(id);}
}
