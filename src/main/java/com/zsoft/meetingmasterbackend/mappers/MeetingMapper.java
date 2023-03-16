package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.MeetingTypeDto;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.MeetingType;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {ProfileMapper.class})
public abstract class MeetingMapper {

    @Autowired protected ProfileMapper profileMapper;
    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "owner", expression = "java(profileMapper.toProfileDto(meeting.getOwner()))")
    @Mapping(target = "type",expression = "java(toMeetingTypeDto(meeting.getType()))")
    public abstract MeetingDTO toMeetingDto(Meeting meeting);


    @Mapping(target = "dailyRepetition",source = "dailyRepetition",qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner", expression = "java(profileMapper.toProfile(meetingDTO.getOwner()))")
    @Mapping(target = "type",expression = "java(toMeetingType(meetingDTO.getType()))")
    public abstract Meeting toMeeting(MeetingDTO meetingDTO);

    @Named("toMeetingType")
    public abstract MeetingType toMeetingType(MeetingTypeDto meetingType);
    @Named("toMeetingTypeDto")
    public abstract MeetingTypeDto toMeetingTypeDto(MeetingType meetingTypeDto);

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
