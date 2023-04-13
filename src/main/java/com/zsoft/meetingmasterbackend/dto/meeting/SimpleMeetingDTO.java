package com.zsoft.meetingmasterbackend.dto.meeting;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


// Used
@Builder
@Setter
@Getter
public class SimpleMeetingDTO {

    private Long id;
    private String name;
    private ProfileDTO owner;
    private Date createdAt;
    private Date beginsAt;
    private Date endsAt;
    private int weekRepetition;
    private int repetitionEndsAfter;
    private Map dailyRepetition=  new LinkedHashMap();
    private MeetingTypeDTO type;
    private Date closedAt;
    private boolean closed;
    private Set<ProfileDTO> participants;
}
