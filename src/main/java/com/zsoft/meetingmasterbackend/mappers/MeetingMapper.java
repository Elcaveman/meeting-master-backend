package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDto;
import com.zsoft.meetingmasterbackend.models.Meeting;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {ProfileMapper.class})
public abstract class MeetingMapper {

    @Autowired protected ProfileMapper profileMapper;
    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "owner", expression = "java(profileMapper.toProfileDto(meeting.getOwner()))")
    @Mapping(target = "type", source = "meeting.type.name")
    public abstract MeetingDTO toMeetingDTO(Meeting meeting);

    @Mapping(target = "owner", expression = "java(meeting.getOwner().getName())")
    @Mapping(target = "type", expression = "java(meeting.getType().getName())")
    public abstract SimpleMeetingDto toSimpleMeetingDto(Meeting meeting);

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
}
