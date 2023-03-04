package com.zsoft.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne // DONE
    @JoinColumn(name = "owner_id")
    @JsonManagedReference
    private Profile owner;
    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_meeting_id")
    @JsonManagedReference
    private Meeting finishedByMeeting;

    @ManyToOne // DONE
    @JoinColumn(name = "finished_by_profile_id")
    @JsonManagedReference
    private Profile finishedByProfile;
    @ManyToMany(mappedBy = "actions") // DONE mapping
    @JsonBackReference
    private Set<Meeting> meetings = new HashSet<>();

    @ManyToOne // DONE
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private ActionType type;
}
