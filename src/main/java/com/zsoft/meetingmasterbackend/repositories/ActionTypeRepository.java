package com.zsoft.meetingmasterbackend.repositories;

import com.zsoft.meetingmasterbackend.models.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionTypeRepository extends JpaRepository<ActionType,Long> {

    ActionType findActionTypeById(Long id);

}
