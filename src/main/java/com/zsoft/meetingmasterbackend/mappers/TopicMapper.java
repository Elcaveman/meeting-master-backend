package com.zsoft.meetingmasterbackend.mappers;

import com.zsoft.meetingmasterbackend.dto.topic.SimpleTopicDTO;
import com.zsoft.meetingmasterbackend.models.Topic;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring"   ,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MeetingMapper.class, Collectors.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TopicMapper {


    public abstract SimpleTopicDTO toSimpleTopicDto(Topic topic);

}
