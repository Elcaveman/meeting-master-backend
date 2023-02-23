package com.example.meetingmasterbackend.repositories;

import com.example.meetingmasterbackend.models.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionRepository extends CrudRepository<Action, Long> {
    List<Action> findAll();
    Action findActionById(Long id);
    List<Action> findActionsByTypeId(Long id);
    List<Action> findAllById(Long[] ids);
}
