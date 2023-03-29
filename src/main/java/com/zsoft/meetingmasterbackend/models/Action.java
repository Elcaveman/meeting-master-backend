package com.zsoft.meetingmasterbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Date createdAt;

    private Date finishedAt;
    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    private Profile owner;
    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_meeting_id")
    private Meeting finishedByMeeting;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_profile_id")
    private Profile finishedByProfile;
    @ManyToMany// DONE
    @JoinTable(name = "meeting_has_action",
            joinColumns = @JoinColumn(name = "action_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    private Set<Meeting> meetings = new HashSet<>(); // only the owner can add references!

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    private ActionType type;
}