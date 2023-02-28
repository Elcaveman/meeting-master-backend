package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting,Long> {

    List<Meeting> findAll();
    Meeting findMeetingById(Long id);
    List<Meeting> findMeetingsByTypeId(Long id);
    List<Meeting> findMeetingsById(Long[] ids);
}
