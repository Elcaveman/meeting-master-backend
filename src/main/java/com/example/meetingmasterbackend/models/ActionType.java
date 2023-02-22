package com.example.meetingmasterbackend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "type") // DONE
    private Set<Action> actions;
}
