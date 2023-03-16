package com.zsoft.meetingmasterbackend.dto.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class ActionUpdateDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Date finishedAt;
    private Date deadline;
}
