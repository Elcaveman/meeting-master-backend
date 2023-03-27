package com.zsoft.meetingmasterbackend.dto.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@Builder
@ToString
public class ActionCreateDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Date deadline;
    private Long type;
    private Long owner;
    private boolean finished;
    private Date finishedAt;
    private Long meeting;
}
