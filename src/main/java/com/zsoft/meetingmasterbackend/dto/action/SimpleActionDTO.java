package com.zsoft.meetingmasterbackend.dto.action;

import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.dto.topic.SimpleTopicDTO;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleActionDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Date deadline;
    private ActionTypeDTO type;
    private ProfileDTO owner;
    private ProfileDTO assignedTo;
    private boolean finished;
    private Date finishedAt;
    private ProfileDTO finishedByProfile;
    private SimpleTopicDTO topic;
}
