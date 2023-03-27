package com.zsoft.meetingmasterbackend.dto.action;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleActionDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Date deadline;
    private Long type;
    private Long owner;
    private boolean finished;
    private Date finishedAt;
}
