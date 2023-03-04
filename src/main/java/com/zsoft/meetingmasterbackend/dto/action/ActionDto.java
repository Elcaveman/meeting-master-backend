package com.zsoft.meetingmasterbackend.dto.action;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDto;
import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import com.zsoft.meetingmasterbackend.models.ActionType;
import com.zsoft.meetingmasterbackend.models.Meeting;
import com.zsoft.meetingmasterbackend.models.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
public class ActionDto {
    private Long id;
    private String name;
    private Date createdAt;
    private ProfileDTO owner;
    private Date deadline;
    private SimpleMeetingDto finishedByMeeting;
    private ProfileDTO finishedByProfile;
    private Set<SimpleMeetingDto> meetings = new HashSet<>();
    private String type;

}
