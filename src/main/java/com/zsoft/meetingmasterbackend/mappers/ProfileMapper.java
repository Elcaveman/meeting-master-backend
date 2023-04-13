package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.models.Profile;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProfileMapper {

    public abstract ProfileDTO toProfileDto(Profile profile);


    public abstract Profile toProfile(ProfileDTO profileDTO);


}
