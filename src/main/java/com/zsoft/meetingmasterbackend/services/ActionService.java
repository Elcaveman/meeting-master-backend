package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.action.ActionCreateDTO;
import com.zsoft.meetingmasterbackend.dto.action.ActionUpdateDTO;
import com.zsoft.meetingmasterbackend.dto.action.SimpleActionDTO;
import com.zsoft.meetingmasterbackend.mappers.ActionMapper;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.repositories.ActionRepository;
import com.zsoft.meetingmasterbackend.repositories.ActionTypeRepository;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    @Autowired
    public ActionService(ActionRepository actionRepository, MeetingRepository meetingRepository, ActionMapper actionMapper, ProfileRepository profileRepository, ActionTypeRepository actionTypeRepository) {
        this.actionRepository = actionRepository;
        this.meetingRepository = meetingRepository;
        this.actionMapper = actionMapper;
        this.profileRepository = profileRepository;
        this.actionTypeRepository = actionTypeRepository;
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
        Optional<Meeting> finishedByMeeting = null;
        Optional<Profile> finishedByProfile = null;
        if(!isNull(actionUpdateDto.getFinishedByMeeting())){
            finishedByMeeting = meetingRepository.findById(actionUpdateDto.getFinishedByMeeting());
            finishedByProfile = profileRepository.findById(actionUpdateDto.getFinishedByProfile());
        }
        Optional<Action> actionOptional = actionRepository.findById(actionUpdateDto.getId());
        SimpleActionDTO result = null;
        if(actionOptional.isPresent()){
            Action action = actionOptional.get();
            actionMapper.updateActionFromDto(actionUpdateDto,action);
            action.setFinishedAt(actionUpdateDto.getFinishedAt());
            if(!isNull(finishedByMeeting) && !isNull(finishedByProfile)){
                finishedByMeeting.ifPresent(action::setFinishedByMeeting);
                finishedByProfile.ifPresent(action::setFinishedByProfile);
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
