package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.action.ActionCreateDTO;
import com.zsoft.meetingmasterbackend.dto.action.ActionUpdateDTO;
import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.mappers.ActionMapper;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.repositories.ActionRepository;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActionService {

    private final ActionRepository actionRepository;
    private final MeetingRepository meetingRepository;
    private final ActionMapper actionMapper;
    @Autowired
    public ActionService(ActionRepository actionRepository, MeetingRepository meetingRepository, ActionMapper actionMapper) {
        this.actionRepository = actionRepository;
        this.meetingRepository = meetingRepository;
        this.actionMapper = actionMapper;
    }

    public List<SimpleActionDTO> getActions(){
        return this.actionRepository.findAll().stream().map(actionMapper::toSimpleActionDto).collect(Collectors.toList());
    }
    public SimpleActionDTO getActionById(Long id){
        return this.actionMapper.toSimpleActionDto(actionRepository.findActionById(id));
    }
    public List<SimpleActionDTO> getActionsByTypeId(Long id){
        return this.actionRepository.findActionsByTypeId(id).stream().map(actionMapper::toSimpleActionDto).collect(Collectors.toList());
    }
    public List<SimpleActionDTO> getActionsById(Long[] ids){
        return this.actionRepository.findAllById(ids).stream().map(actionMapper::toSimpleActionDto).collect(Collectors.toList());
    }

    public List<SimpleActionDTO> getActionsByMeetingId(Long meetingId) {
        return this.actionRepository.findByMeetings_Id(meetingId).stream().map(actionMapper::toSimpleActionDto).collect(Collectors.toList());
    }

    public void updateAction(ActionUpdateDTO actionUpdateDto){
        Optional<Action> actionOptional = actionRepository.findById(actionUpdateDto.getId());
        if(actionOptional.isPresent()){
            System.out.println(actionUpdateDto.toString());
            Action action = actionOptional.get();
            actionMapper.updateActionFromDto(actionUpdateDto,action);
            actionRepository.save(action);
        }
    }

    public ActionCreateDTO createAction(ActionCreateDTO actionCreateDTO){
        Action actionToCreate = actionMapper.toAction(actionCreateDTO);
        actionToCreate.setMeetings(new HashSet<>());
        Meeting meeting = meetingRepository.findMeetingById(actionCreateDTO.getMeeting());
        actionToCreate.getMeetings().add(meeting);
        return actionMapper.toActionCreateDto(this.actionRepository.save(actionToCreate));
    }

    public void deleteAction(Long id){
        actionRepository.deleteById(id);
    }
}
