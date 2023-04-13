package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {

    List<Meeting> findAll();
    Meeting findMeetingById(Long id);
    List<Meeting> findMeetingsByTypeId(Long id);
    List<Meeting> findMeetingsById(Long[] ids);
}
