package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.action.ActionDTO;
import com.zsoft.meetingmasterbackend.mappers.ActionMapper;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionService {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    @Autowired
    public ActionService(ActionRepository actionRepository, ActionMapper actionMapper) {
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
    }

    public List<ActionDTO> getActions(){
        return this.actionRepository.findAll().stream().map(actionMapper::actionToActionDTO).collect(Collectors.toList());
    }
    public ActionDTO getActionById(Long id){
        return this.actionMapper.actionToActionDTO(actionRepository.findActionById(id));
    }
    public List<ActionDTO> getActionsByTypeId(Long id){
        return this.actionRepository.findActionsByTypeId(id).stream().map(actionMapper::actionToActionDTO).collect(Collectors.toList());
    }
    public List<ActionDTO> getActionsById(Long[] ids){
        return this.actionRepository.findAllById(ids).stream().map(actionMapper::actionToActionDTO).collect(Collectors.toList());
    }
}
