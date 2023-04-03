package com.zsoft.meetingmasterbackend.dto.topic;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTopicDTO {
    private Long id;
    private String name;
}
