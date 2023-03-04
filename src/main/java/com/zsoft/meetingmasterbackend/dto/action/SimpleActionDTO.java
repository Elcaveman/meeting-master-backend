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
    private String type;
}
