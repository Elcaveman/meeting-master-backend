package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.models.Profile;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProfileMapper {
    @Mapping(target = "id",source = "profile.id")
    @Mapping(target = "name",source = "profile.name")
    @Mapping(target = "email",source = "profile.email")
    public abstract ProfileDTO toProfileDto(Profile profile);

    @Mapping(target = "id",source = "profile.id")
    @Mapping(target = "name",source = "profile.name")
    @Mapping(target = "email",source = "profile.email")
    public abstract ProfileDTO toSimpleProfileDto(Profile profile);


}
