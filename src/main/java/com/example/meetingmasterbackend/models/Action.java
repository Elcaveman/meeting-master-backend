package com.example.meetingmasterbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Meeting getFinishedByMeeting() {
        return finishedByMeeting;
    }

    public void setFinishedByMeeting(Meeting finishedByMeeting) {
        this.finishedByMeeting = finishedByMeeting;
    }

    public Profile getFinishedByProfile() {
        return finishedByProfile;
    }

    public void setFinishedByProfile(Profile finishedByProfile) {
        this.finishedByProfile = finishedByProfile;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }
}
