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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "ends_after")
    private int repetitionEndsAfter;
    @ManyToMany(mappedBy = "meetings")
    private Set<Action> actions = new HashSet<>();

    @OneToMany(mappedBy = "finishedByMeeting") // DONE
    @JsonBackReference
    private Set<Action> finishedActions = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private MeetingType type;

}

