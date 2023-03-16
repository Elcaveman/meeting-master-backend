package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.MeetingTypeDto;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.MeetingType;
import com.zsoft.meetingmasterbackend.models.Profile;
import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
import com.zsoft.meetingmasterbackend.repositories.MeetingTypeRepository;
import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
import com.zsoft.meetingmasterbackend.services.ProfileService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {ProfileMapper.class},
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class MeetingMapper {

    @Autowired protected ProfileMapper profileMapper;
    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "owner", expression = "java(profileMapper.toProfileDto(meeting.getOwner()))")
    @Mapping(target = "type",expression = "java(toMeetingTypeDto(meeting.getType()))")
    public abstract MeetingDTO toMeetingDto(Meeting meeting);

    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "owner", source = "owner.id")
    @Mapping(target = "type",source = "type.id")
    public abstract SimpleMeetingDTO toSimpleMeetingDto(Meeting meeting);


    @Mapping(target = "dailyRepetition",source = "dailyRepetition",qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner", expression = "java(profileMapper.toProfile(meetingDTO.getOwner()))")
    @Mapping(target = "type",expression = "java(toMeetingType(meetingDTO.getType()))")
    public abstract Meeting toMeeting(MeetingDTO meetingDTO);

    @Mapping(target = "dailyRepetition",source = "dailyRepetition", qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner.id",source = "owner")
    @Mapping(target = "type.id",source = "type")
    public abstract Meeting toMeeting(SimpleMeetingDTO simpleMeetingDTO);

    @Named("toMeetingType")
    public abstract MeetingType toMeetingType(MeetingTypeDto meetingType);
    @Named("toMeetingTypeDto")
    public abstract MeetingTypeDto toMeetingTypeDto(MeetingType meetingTypeDto);

    @Mapping(target = "dailyRepetition",source = "dailyRepetition", qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner",ignore = true)
    @Mapping(target = "type",ignore = true)
    @Mapping(target = "id",ignore = true)
    // NOT SURE
    public abstract void updateMeetingFromSimpleMeetingDto(SimpleMeetingDTO simpleMeetingDTO, @MappingTarget Meeting meeting);

    public Map getDailyRepetition(String dailyRepetition) {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Map<String, Boolean> repetitionDays = new LinkedHashMap<>();
        if (dailyRepetition != null) {
            for (int i = 0; i < 7 && i < dailyRepetition.length(); i++) {
                boolean isRepeating = dailyRepetition.charAt(i) == '1';
                if(isRepeating) repetitionDays.put(daysOfWeek[i], true);
            }
            return repetitionDays;
        } else return null;
    }

    @Named("setDailyRepetition")
    public String setDailyRepetition(Map dailyRepetition){
        // TODO
        return "";
    }
}