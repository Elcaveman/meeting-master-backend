package com.example.meetingmasterbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public Long getId() {
        return id;
    }
}
