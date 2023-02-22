package com.example.meetingmasterbackend.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date createdAt;
    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    private Profile owner;
    private Date deadline;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_meeting_id")
    private Meeting finishedByMeeting;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_profile_id")
    private Profile finishedByProfile;
    @ManyToMany(mappedBy = "actions") // DONE mapping
    private Set<Meeting> meetings = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    private ActionType type;




    public Long getId() {
        return id;
    }
}
