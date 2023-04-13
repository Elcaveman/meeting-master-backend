package com.zsoft.meetingmasterbackend.services;

import com.zsoft.meetingmasterbackend.dto.topic.SimpleTopicDTO;
import com.zsoft.meetingmasterbackend.mappers.TopicMapper;
import com.zsoft.meetingmasterbackend.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Autowired
    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    public List<SimpleTopicDTO> getTopics(){
        return topicRepository.findAll().stream().map(topicMapper::toSimpleTopicDto).collect(Collectors.toList());
    }

    public List<SimpleTopicDTO> getTopicsByName(String name){
        return topicRepository.findDistinctTop3ByNameContainingIgnoreCase(name).stream()
                .map(topicMapper::toSimpleTopicDto).collect(Collectors.toList());
    }
}
