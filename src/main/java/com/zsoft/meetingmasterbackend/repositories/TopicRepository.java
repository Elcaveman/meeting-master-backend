package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    List<Topic> findAll();

    List<Topic> findTopicsByMeetings_Id(Long id);

}
