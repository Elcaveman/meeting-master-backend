package com.zsoft.meetingmasterbackend.dto.action;

import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
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
    private boolean finished;
    private Date finishedAt;
    private ProfileDTO finishedByProfile;
}
