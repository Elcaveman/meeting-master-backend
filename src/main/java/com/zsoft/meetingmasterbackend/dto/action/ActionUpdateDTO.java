package com.zsoft.meetingmasterbackend.dto.action;

import com.zsoft.meetingmasterbackend.dto.topic.SimpleTopicDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class ActionUpdateDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private boolean finished;
    private Date finishedAt;
    private Date deadline;
    private Long finishedByMeeting;
    private Long finishedByProfile;
    private Long assignedTo;
    private Long topic;
}
