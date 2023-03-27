package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.mappers.MeetingMapper;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.MeetingType;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import com.zsoft.meetingmasterbackend.repositories.MeetingTypeRepository;
import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ProfileRepository profileRepository;
    private final MeetingTypeRepository meetingTypeRepository;
    private final MeetingMapper meetingMapper;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, ProfileRepository profileRepository, MeetingTypeRepository meetingTypeRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.profileRepository = profileRepository;
        this.meetingTypeRepository = meetingTypeRepository;
        this.meetingMapper = meetingMapper;
    }

    public List<MeetingDTO> getMeetings()
    {
        return this.meetingRepository.findAll().stream().map(meetingMapper::toMeetingDto).collect(Collectors.toList());

    }

    public MeetingDTO getMeetingById(Long id){
        return meetingMapper.toMeetingDto(this.meetingRepository.findMeetingById(id));
    }

    public List<MeetingDTO> getMeetingsByTypeId(Long id){
        return this.meetingRepository.findMeetingsByTypeId(id).stream().map(meetingMapper::toMeetingDto).collect(Collectors.toList());
    }

    public SimpleMeetingDTO createMeeting(SimpleMeetingDTO simpleMeetingDTO){
        return meetingMapper.toSimpleMeetingDto(this.meetingRepository.save(meetingMapper.toMeeting(simpleMeetingDTO)));
    }

    public SimpleMeetingDTO updateMeeting(Long id, SimpleMeetingDTO simpleMeetingDTO){
        Meeting meetingToUpdate = meetingRepository.findMeetingById(id);
        // fetch new owner
        Profile newOwner = profileRepository.findProfileById(simpleMeetingDTO.getOwner());
        // fetch new meeting type
        MeetingType newMeetingType = meetingTypeRepository.findMeetingTypeById(simpleMeetingDTO.getType());
        // map dto to meeting
        meetingMapper.updateMeetingFromSimpleMeetingDto(simpleMeetingDTO,meetingToUpdate);
        // set new type
        meetingToUpdate.setType(newMeetingType);
        // set new owner
        meetingToUpdate.setOwner(newOwner);
        // save to repo
        return meetingMapper.toSimpleMeetingDto(this.meetingRepository.save(meetingToUpdate));
    }

    public void deleteMeeting(Long id){
        meetingRepository.deleteById(id);
    }
}
