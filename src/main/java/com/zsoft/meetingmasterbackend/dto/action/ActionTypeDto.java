package com.zsoft.meetingmasterbackend.dto.action;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActionTypeDto {
    private Long id;
    private String name;
}
