package com.zsoft.meetingmasterbackend.dto.meeting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MeetingTypeDto {
    private Long id;
    private String name;
}
