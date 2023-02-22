package com.example.meetingmasterbackend.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    private Profile owner;
    private Date createdAt;
    private Date beginsAt;
    private String time;
    private boolean isRepeated;
    private int repeatedEvery;
    private boolean isWeeklyRepeated;
    private boolean isMonthlyRepeated;
    private String dailyRepetition;
    private Date endsAt;
    private int endsAfter;
    @ManyToMany // DONE
    @JoinTable(name = "meeting_has_action",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private Set<Action> actions = new HashSet<>();

    @OneToMany(mappedBy = "finishedByMeeting") // DONE
    private Set<Action> finishedActions = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    private MeetingType type;

    public Long getId() {
        return id;
    }
}
