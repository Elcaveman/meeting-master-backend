package com.example.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    @JsonManagedReference
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
    @JsonManagedReference
    private Set<Action> actions = new HashSet<>();

    @OneToMany(mappedBy = "finishedByMeeting") // DONE
    @JsonBackReference
    private Set<Action> finishedActions = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private MeetingType type;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(Date beginsAt) {
        this.beginsAt = beginsAt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public int getRepeatedEvery() {
        return repeatedEvery;
    }

    public void setRepeatedEvery(int repeatedEvery) {
        this.repeatedEvery = repeatedEvery;
    }

    public boolean isWeeklyRepeated() {
        return isWeeklyRepeated;
    }

    public void setWeeklyRepeated(boolean weeklyRepeated) {
        isWeeklyRepeated = weeklyRepeated;
    }

    public boolean isMonthlyRepeated() {
        return isMonthlyRepeated;
    }

    public void setMonthlyRepeated(boolean monthlyRepeated) {
        isMonthlyRepeated = monthlyRepeated;
    }

    public String getDailyRepetition() {
        return dailyRepetition;
    }

    public void setDailyRepetition(String dailyRepetition) {
        this.dailyRepetition = dailyRepetition;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public int getEndsAfter() {
        return endsAfter;
    }

    public void setEndsAfter(int endsAfter) {
        this.endsAfter = endsAfter;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Action> getFinishedActions() {
        return finishedActions;
    }

    public void setFinishedActions(Set<Action> finishedActions) {
        this.finishedActions = finishedActions;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }
}
