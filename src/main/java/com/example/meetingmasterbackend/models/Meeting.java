package com.example.meetingmasterbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Long getId() {
        return id;
    }
}
