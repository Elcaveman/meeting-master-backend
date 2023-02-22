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
}
