package com.zsoft.meetingmasterbackend.dto.action;

import com.zsoft.meetingmasterbackend.dto.meeting.MeetingDTO;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
public class ActionDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private boolean finished;
    private Date finishedAt;
    private ProfileDTO owner;
    private Date deadline;
    private MeetingDTO finishedByMeeting;
    private ProfileDTO finishedByProfile;
    private Set<MeetingDTO> meetings = new HashSet<>();
    private String type;

}
