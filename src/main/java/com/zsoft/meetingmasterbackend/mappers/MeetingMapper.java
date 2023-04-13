package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingCreateDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.MeetingTypeDTO;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.MeetingType;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ProfileMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class MeetingMapper {

    @Autowired protected ProfileMapper profileMapper;
    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "type",expression = "java(toMeetingTypeDto(meeting.getType()))")
    @Mapping(target = "closed",expression = "java(meeting.getClosedAt()!=null)")
    public abstract SimpleMeetingDTO toSimpleMeetingDto(Meeting meeting);

    @Mapping(target = "dailyRepetition", expression = "java(getDailyRepetition(meeting.getDailyRepetition()))")
    @Mapping(target = "closed",expression = "java(meeting.getClosedAt()!=null)")
    @Mapping(target = "owner", source = "owner.id")
    @Mapping(target = "type",source = "type.id")
//    @Mapping(target = "participants",ignore = true)
    public abstract MeetingCreateDTO toMeetingCreateDto(Meeting meeting);


    @Mapping(target = "dailyRepetition",source = "dailyRepetition",qualifiedByName = "setDailyRepetition")
    @Mapping(target = "type",expression = "java(toMeetingType(simpleMeetingDTO.getType()))")
    public abstract Meeting toMeeting(SimpleMeetingDTO simpleMeetingDTO);

    @Mapping(target = "dailyRepetition",source = "dailyRepetition", qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner.id",source = "owner")
    @Mapping(target = "type.id",source = "type")
    public abstract Meeting toMeeting(MeetingCreateDTO meetingCreateDTO);

    @Named("toMeetingType")
    public abstract MeetingType toMeetingType(MeetingTypeDTO meetingType);
    @Named("toMeetingTypeDto")
    public abstract MeetingTypeDTO toMeetingTypeDto(MeetingType meetingTypeDto);

    @Mapping(target = "dailyRepetition",source = "dailyRepetition", qualifiedByName = "setDailyRepetition")
    @Mapping(target = "owner",ignore = true)
    @Mapping(target = "type",ignore = true)
    @Mapping(target = "id",ignore = true)
    // NOT SURE
    public abstract void updateMeetingFromMeetingCreateDto(MeetingCreateDTO meetingCreateDTO, @MappingTarget Meeting meeting);

    public Map getDailyRepetition(String dailyRepetition) {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Map<String, Boolean> repetitionDays = new LinkedHashMap<>();
        if (dailyRepetition != null) {
            for (int i = 0; i < 7 && i < dailyRepetition.length(); i++) {
                boolean isRepeating = dailyRepetition.charAt(i) == '1';
                repetitionDays.put(daysOfWeek[i],isRepeating);
            }
            return repetitionDays;
        } else return null;
    }

    @Named("setDailyRepetition")
    public String setDailyRepetition(Map<String,Boolean> dailyRepetition){
        String stringDailyRepetition="";
        if (!isNull(dailyRepetition)){
            for(Boolean value : dailyRepetition.values()){
                stringDailyRepetition+=(value)? "1":"0";
            }
        }
        return stringDailyRepetition;
    }
}