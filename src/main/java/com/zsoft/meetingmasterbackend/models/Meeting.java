package com.zsoft.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    @JsonManagedReference
    private Profile owner;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "begins_at")
    private Date beginsAt;

    @Column(name = "week_repetition")
    private int weekRepetition;

    @Column(name = "month_repetition")
    private int monthRepetition;

    @Column(name = "daily_repetition")
    private String dailyRepetition; // 7 characters

    @Column(name = "ends_at")
    private Date endsAt;

    @Column(name = "closed_at")
    private Date closedAt;

    @Column(name = "ends_after")
    private int repetitionEndsAfter;

    @ManyToMany(mappedBy = "meetings")
    private Set<Action> actions = new HashSet<>();

    // a meeting has many topics, inside a meeting we could create topic,
    // and then create actions inside these topics
//    @ManyToMany(mappedBy = "meetings")
//    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "finishedByMeeting") // DONE
    @JsonBackReference
    private Set<Action> finishedActions = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private MeetingType type;

    @ManyToMany
    @JoinTable(name = "meeting_has_participant",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Profile> participants = new HashSet<>();

}