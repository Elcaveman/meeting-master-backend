package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.action.ActionCreateDTO;
import com.zsoft.meetingmasterbackend.dto.action.ActionUpdateDTO;
import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.mappers.ActionMapper;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.models.Topic;
import com.zsoft.meetingmasterbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ActionService {

    private final ActionRepository actionRepository;
    private final MeetingRepository meetingRepository;
    private final ActionMapper actionMapper;
    private final ProfileRepository profileRepository;
    private final ActionTypeRepository actionTypeRepository;
    private final TopicRepository topicRepository;
    @Autowired
    public ActionService(ActionRepository actionRepository, MeetingRepository meetingRepository, ActionMapper actionMapper, ProfileRepository profileRepository, ActionTypeRepository actionTypeRepository, TopicRepository topicRepository) {
        this.actionRepository = actionRepository;
        this.meetingRepository = meetingRepository;
        this.actionMapper = actionMapper;
        this.profileRepository = profileRepository;
        this.actionTypeRepository = actionTypeRepository;
        this.topicRepository = topicRepository;
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

    public SimpleActionDTO updateAction(ActionUpdateDTO actionUpdateDto){
        Optional<Action> actionOptional = actionRepository.findById(actionUpdateDto.getId());
        SimpleActionDTO result = null;
        if(actionOptional.isPresent()){
            Action action = actionOptional.get();
            actionMapper.updateActionFromDto(actionUpdateDto,action);
            action.setFinishedAt(actionUpdateDto.getFinishedAt());
            if(!isNull(actionUpdateDto.getTopic())) {
                Optional<Topic> topic = topicRepository.findById(actionUpdateDto.getTopic());
                topic.ifPresent(action::setTopic);
            }
            if(!isNull(actionUpdateDto.getFinishedByMeeting())) {
                Optional<Meeting> finishedByMeeting = meetingRepository.findById(actionUpdateDto.getFinishedByMeeting());
                finishedByMeeting.ifPresent(action::setFinishedByMeeting);
            }
            if(!isNull(actionUpdateDto.getFinishedByProfile())) {
                Optional<Profile> finishedByProfile = profileRepository.findById(actionUpdateDto.getFinishedByProfile());
                finishedByProfile.ifPresent(action::setFinishedByProfile);
            }
            if(!isNull(actionUpdateDto.getAssignedTo())) {
                Optional<Profile> assignedTo = profileRepository.findById(actionUpdateDto.getAssignedTo());
                assignedTo.ifPresent(action::setAssignedTo);
            }
            result = actionMapper.toSimpleActionDto(actionRepository.save(action));
        }

        return result;
    }

    public SimpleActionDTO createAction(ActionCreateDTO actionCreateDTO){
        Action actionToCreate = actionMapper.toAction(actionCreateDTO);
        actionToCreate.setMeetings(new HashSet<>());
        actionToCreate.getMeetings().add(meetingRepository.findMeetingById(actionCreateDTO.getMeeting()));
        actionToCreate.setOwner(profileRepository.findProfileById(actionCreateDTO.getOwner()));
        actionToCreate.setType(actionTypeRepository.findActionTypeById(actionCreateDTO.getType()));
        return actionMapper.toSimpleActionDto(this.actionRepository.save(actionToCreate));
    }

    public void deleteAction(Long id){
        actionRepository.deleteById(id);
    }
}
