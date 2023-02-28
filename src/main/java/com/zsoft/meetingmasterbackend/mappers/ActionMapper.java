package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.action.ActionDTO;
import com.zsoft.meetingmasterbackend.models.Action;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ActionMapper {
    @Mapping(target = "id",source = "action.id")
    @Mapping(target = "name",source = "action.name")
    @Mapping(target = "createdAt",source = "action.createdAt")
    @Mapping(target = "deadline",source = "action.deadline")
    @Mapping(target = "type",source = "action.type.name")
    public abstract ActionDTO actionToActionDTO(Action action);

}
