package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.MeetingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingType,Long> {

}
