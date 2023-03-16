package com.zsoft.meetingmasterbackend.dto.action;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActionTypeDTO {
    private Long id;
    private String name;
}
