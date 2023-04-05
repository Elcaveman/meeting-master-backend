package com.zsoft.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner") // DONE
    @JsonBackReference
    private Set<Action> actions;

    @OneToMany(mappedBy = "owner") // DONE
    @JsonBackReference
    private Set<Meeting> meetings;

    @OneToMany(mappedBy = "finishedByProfile") // DONE
    @JsonBackReference
    private Set<Action> finishedActions;

    @OneToMany(mappedBy = "assignedTo")
    @JsonBackReference
    private Set<Action> assignedActions;

    @ManyToMany(mappedBy = "participants")
    @JsonBackReference
    private Set<Meeting> participateMeetings = new HashSet<>();
}
