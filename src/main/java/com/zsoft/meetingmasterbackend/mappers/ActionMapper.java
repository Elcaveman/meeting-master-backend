package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.action.*;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.models.Action;
import com.zsoft.meetingmasterbackend.models.ActionType;
import com.zsoft.meetingmasterbackend.models.Meeting;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring"   ,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MeetingMapper.class, ProfileMapper.class,Collectors.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ActionMapper {
    @Autowired
    protected MeetingMapper meetingMapper;
    @Autowired
    protected ProfileMapper profileMapper;
    @Mapping(target = "id",source = "action.id")
    @Mapping(target = "name",source = "action.name")
    @Mapping(target = "createdAt",source = "action.createdAt")
    @Mapping(target = "deadline",source = "action.deadline")
    @Mapping(target = "type",expression = "java(toActionTypeDto(action.getType()))")
    @Mapping(target = "owner",expression = "java(profileMapper.toSimpleProfileDto(action.getOwner()))")
    @Mapping(target = "finished",expression = "java(action.getFinishedAt()!=null)")
    public abstract SimpleActionDTO toSimpleActionDto(Action action);

    public abstract ActionTypeDTO toActionTypeDto(ActionType actionType);

    @Mapping(target = "id",source = "action.id")
    @Mapping(target = "name",source = "action.name")
    @Mapping(target = "createdAt",source = "action.createdAt")
    @Mapping(target = "deadline",source = "action.deadline")
    @Mapping(target = "type",source = "action.type.id   ")
    @Mapping(target = "owner",source = "action.owner.id")
    @Mapping(target = "finished",expression = "java(action.getFinishedAt()!=null)")
    @Mapping(target = "meeting",ignore = true) // ignore meetings when creation
    public abstract ActionCreateDTO toActionCreateDto(Action action);


    @Mapping(target = "action.name", source = "actionUpdateDto.name")
    @Mapping(target = "action.createdAt", source = "actionUpdateDto.createdAt")
    @Mapping(target = "action.deadline", source = "actionUpdateDto.deadline")
    @Mapping(target = "action.finishedAt", source = "actionUpdateDto.finishedAt")
    public abstract Action updateActionFromDto(ActionUpdateDTO actionUpdateDto, @MappingTarget Action action);

    @Mapping(target = "owner.id",source = "owner")
    @Mapping(target = "type.id",source = "type")
    @Mapping(target = "meetings",ignore = true) // meetings are set in the service !
    public abstract Action toAction(ActionCreateDTO actionCreateDTO);

    Set<SimpleMeetingDTO> toMeetingDtos(Set<Meeting> meetings) {
        return meetings.stream().map( meeting -> meetingMapper.toSimpleMeetingDto(meeting)).collect( Collectors.toSet() );
    }



//    @Named("toSimpleMeetingDtoSet")
//    Set<SimpleMeetingDto> toSimpleMeetingDtoSet(Set<Meeting> meetings){
//        return meetings.stream().map(MeetingMapper::toSimpleMeetingDto).collect(Collectors.toSet());
//    }
}
