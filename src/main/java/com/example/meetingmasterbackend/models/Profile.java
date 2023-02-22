package com.example.meetingmasterbackend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "owner") // DONE
    private Set<Action> actions;

    @OneToMany(mappedBy = "owner") // DONE
    private Set<Meeting> meetings;

    @OneToMany(mappedBy = "finishedByProfile") // DONE
    private Set<Action> finishedActions;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Set<Action> getFinishedActions() {
        return finishedActions;
    }

    public void setFinishedActions(Set<Action> finishedActions) {
        this.finishedActions = finishedActions;
    }
}
