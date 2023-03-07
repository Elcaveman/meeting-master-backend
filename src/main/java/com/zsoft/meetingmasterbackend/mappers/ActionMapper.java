package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.action.*;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDto;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.models.Meeting;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring"   ,injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {MeetingMapper.class, ProfileMapper.class,Collectors.class})
public abstract class ActionMapper {
    @Autowired
    protected MeetingMapper meetingMapper;
    @Autowired
    protected ProfileMapper profileMapper;
    @Mapping(target = "id",source = "action.id")
    @Mapping(target = "name",source = "action.name")
    @Mapping(target = "createdAt",source = "action.createdAt")
    @Mapping(target = "deadline",source = "action.deadline")
    @Mapping(target = "type",source = "action.type.name")
    public abstract SimpleActionDTO toSimpleActionDto(Action action);


    @Mapping(target = "action.name", source = "actionUpdateDto.name")
    @Mapping(target = "action.createdAt", source = "actionUpdateDto.createdAt")
    @Mapping(target = "action.deadline", source = "actionUpdateDto.deadline")
    @Mapping(target = "action.finished", source = "actionUpdateDto.finished")
    public abstract Action updateActionFromDto(ActionUpdateDto actionUpdateDto, @MappingTarget Action action);

    @Mapping(target = "type",source = "action.type.name")
    @Mapping(target= "finishedByMeeting", expression="java(meetingMapper.toSimpleMeetingDto(action.getFinishedByMeeting()))")
    @Mapping(target= "finishedByProfile", expression="java(profileMapper.toSimpleProfileDto(action.getFinishedByProfile()))")
    @Mapping(target= "owner", expression="java(profileMapper.toSimpleProfileDto(action.getOwner()))")
    @Mapping(target="meetings",expression = "java(toMeetingDtos(action.getMeetings()))")
    public abstract ActionDto toActionDto(Action action);

    Set<SimpleMeetingDto> toMeetingDtos(Set<Meeting> meetings) {
        return meetings.stream().map( meeting -> meetingMapper.toSimpleMeetingDto(meeting) ).collect( Collectors.toSet() );
    }



//    @Named("toSimpleMeetingDtoSet")
//    Set<SimpleMeetingDto> toSimpleMeetingDtoSet(Set<Meeting> meetings){
//        return meetings.stream().map(MeetingMapper::toSimpleMeetingDto).collect(Collectors.toSet());
//    }
}
