package com.example.meetingmasterbackend.services;

import com.example.meetingmasterbackend.models.Action;
import com.example.meetingmasterbackend.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ActionService {

    private final ActionRepository actionRepository;
    @Autowired
    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> getActions(){return this.actionRepository.findAll();}
    public Action getActionById(Long id){return this.actionRepository.findActionById(id);}
    public List<Action> getActionsByTypeId(Long id){return this.actionRepository.findActionsByTypeId(id);}
    public List<Action> getActionsById(Long[] ids){return this.actionRepository.findAllById(ids);}
}
