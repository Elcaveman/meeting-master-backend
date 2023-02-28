package com.zsoft.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "meeting_type")
public class MeetingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type") // DONE
    @JsonBackReference
    private Set<Meeting> meetings;
}
