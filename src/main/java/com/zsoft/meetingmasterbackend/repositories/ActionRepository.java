package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ActionRepository extends CrudRepository<Action, Long> {
    List<Action> findAll();
    Action findActionById(Long id);
    List<Action> findActionsByTypeId(Long id);
    List<Action> findAllById(Long[] ids);

    List<Action> findByMeetings_Id(Long meetingId);
}
