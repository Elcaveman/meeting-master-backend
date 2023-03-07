package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findAll();
    Action findActionById(Long id);
    List<Action> findActionsByTypeId(Long id);
    List<Action> findAllById(Long[] ids);
}
