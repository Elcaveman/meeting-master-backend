package com.zsoft.meetingmasterbackend.dto.meeting;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import lombok.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private Long id;
    private String name;
    private ProfileDTO owner;
    private Date createdAt;
    private Date beginsAt;
    private Date endsAt;
    private int weekRepetition;
    private int repetitionEndsAfter;
    private Map dailyRepetition=  new LinkedHashMap();
    private MeetingTypeDto type;
}
