package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.mappers.MeetingMapper;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
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

    public MeetingDTO createMeeting(MeetingDTO meetingDTO){
        return meetingMapper.toMeetingDto(this.meetingRepository.save(meetingMapper.toMeeting(meetingDTO)));
    }
}
