package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.action.ActionDto;
import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.mappers.ActionMapper;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ActionDto> getActions(){
        return this.actionRepository.findAll().stream().map(actionMapper::toActionDto).collect(Collectors.toList());
    }
    public SimpleActionDTO getActionById(Long id){
        return this.actionMapper.toSimpleActionDto(actionRepository.findActionById(id));
    }
    public List<ActionDto> getActionsByTypeId(Long id){
        return this.actionRepository.findActionsByTypeId(id).stream().map(actionMapper::toActionDto).collect(Collectors.toList());
    }
    public List<SimpleActionDTO> getActionsById(Long[] ids){
        return this.actionRepository.findAllById(ids).stream().map(actionMapper::toSimpleActionDto).collect(Collectors.toList());
    }

    public List<ActionDto> getActionsByMeetingId(Long meetingId) {
        return this.actionRepository.findByMeetings_Id(meetingId).stream().map(actionMapper::toActionDto).collect(Collectors.toList());
    }
}
