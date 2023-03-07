package com.zsoft.meetingmasterbackend.dto.meeting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Builder
@Setter
@Getter
public class SimpleMeetingDto {
    private Long id;
    private String name;
    private String owner;
    private Date createdAt;
    private Date beginsAt;
    private Date endsAt;
    private int weekRepetition;
    private int repetitionEndsAfter;
    private Map dailyRepetition=  new LinkedHashMap();
    private String type;
}
