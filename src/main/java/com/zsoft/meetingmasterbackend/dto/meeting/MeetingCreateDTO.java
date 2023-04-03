package com.zsoft.meetingmasterbackend.dto.meeting;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCreateDTO {

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
    private Date closedAt;
    private boolean closed;
}
