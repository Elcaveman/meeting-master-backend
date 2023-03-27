package com.zsoft.meetingmasterbackend.dto.meeting;

import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


// Used
@Builder
@Setter
@Getter
public class SimpleMeetingDTO {

    private Long id;
    private String name;
    private Long owner;
    private Date createdAt;
    private Date beginsAt;
    private Date endsAt;
    private int weekRepetition;
    private int repetitionEndsAfter;
    private Map dailyRepetition=  new LinkedHashMap();
    private Long type;
}