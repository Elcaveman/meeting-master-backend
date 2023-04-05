package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingCreateDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.mappers.MeetingMapper;
import com.zsoft.meetingmasterbackend.mappers.ProfileMapper;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.MeetingType;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import com.zsoft.meetingmasterbackend.repositories.MeetingTypeRepository;
import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ProfileRepository profileRepository;
    private final MeetingTypeRepository meetingTypeRepository;
    private final MeetingMapper meetingMapper;
    private final ProfileMapper profileMapper;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, ProfileRepository profileRepository, MeetingTypeRepository meetingTypeRepository, MeetingMapper meetingMapper, ProfileMapper profileMapper) {
        this.meetingRepository = meetingRepository;
        this.profileRepository = profileRepository;
        this.meetingTypeRepository = meetingTypeRepository;
        this.meetingMapper = meetingMapper;
        this.profileMapper = profileMapper;
    }

    public List<SimpleMeetingDTO> getMeetings()
    {
        return this.meetingRepository.findAll().stream().map(meetingMapper::toSimpleMeetingDto).collect(Collectors.toList());

    }

    public SimpleMeetingDTO getMeetingById(Long id){
        return meetingMapper.toSimpleMeetingDto(this.meetingRepository.findMeetingById(id));
    }

    public List<SimpleMeetingDTO> getMeetingsByTypeId(Long id){
        return this.meetingRepository.findMeetingsByTypeId(id).stream().map(meetingMapper::toSimpleMeetingDto).collect(Collectors.toList());
    }

    public SimpleMeetingDTO createMeeting(MeetingCreateDTO meetingCreateDTO){
        Meeting meeting_to_save = meetingMapper.toMeeting(meetingCreateDTO);
        meeting_to_save.setOwner(profileRepository.findProfileById(meetingCreateDTO.getOwner()));
        meeting_to_save.setType(meetingTypeRepository.findMeetingTypeById(meetingCreateDTO.getType()));
        return meetingMapper.toSimpleMeetingDto(this.meetingRepository.save(meeting_to_save));
    }

    public MeetingCreateDTO updateMeeting(Long id, MeetingCreateDTO meetingCreateDTO){
        Meeting meetingToUpdate = meetingRepository.findMeetingById(id);
        // fetch new owner
        Optional<Profile> newOwner = Optional.ofNullable(profileRepository.findProfileById(meetingCreateDTO.getOwner()));
        // fetch new meeting type
        Optional<MeetingType> newMeetingType = Optional.ofNullable(meetingTypeRepository.findMeetingTypeById(meetingCreateDTO.getType()));
        // map dto to meeting
        meetingMapper.updateMeetingFromMeetingCreateDto(meetingCreateDTO,meetingToUpdate);
        // set new owner
        newOwner.ifPresent(meetingToUpdate::setOwner);
        // set new type
        newMeetingType.ifPresent(meetingToUpdate::setType);
        meetingToUpdate.setClosedAt(meetingCreateDTO.getClosedAt());
        // save to repo
        return meetingMapper.toMeetingCreateDto(this.meetingRepository.save(meetingToUpdate));
    }

    public void deleteMeeting(Long id){
        meetingRepository.deleteById(id);
    }

    public SimpleMeetingDTO addParticipantToMeeting(Long meetingId, ProfileDTO participantDto) {
        Meeting meeting = meetingRepository.findMeetingById(meetingId);
        Optional<Profile> participant = Optional.ofNullable(profileRepository.findProfileById(participantDto.getId()));
        participant.ifPresentOrElse(meeting.getParticipants()::add, ()-> {
            Profile newProfile = profileMapper.toProfile(participantDto);
            meeting.getParticipants().add(newProfile);
            profileRepository.save(newProfile);
        });
        return meetingMapper.toSimpleMeetingDto(meetingRepository.save(meeting));
    }

    public void deleteParticipant(Long meeting_id, Long participant_id) {
        //TODO
        Meeting meeting = meetingRepository.findMeetingById(meeting_id);
        Optional<Profile> participant = Optional.ofNullable(profileRepository.findProfileById(participant_id));
        participant.ifPresent(meeting.getParticipants()::remove);
        meetingRepository.save(meeting);
    }
}
