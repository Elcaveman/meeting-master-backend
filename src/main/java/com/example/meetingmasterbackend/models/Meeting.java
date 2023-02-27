package com.example.meetingmasterbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne // DONE
    @JoinColumn(name = "owner")
    @JsonManagedReference
    private Profile owner;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "begins_at")
    private Date beginsAt;
    @Column(name = "week_repetition")
    private int weekRepetition;
    @Column(name = "month_repetition")
    private int monthRepetition;
    @Column(name = "daily_repetition")
    private String dailyRepetition; // 7 characters
    @Column(name = "ends_at")
    private Date endsAt;
    @Column(name = "ends_after")
    private int endsAfter;
    @ManyToMany(cascade = {CascadeType.ALL}) // DONE
    @JoinTable(name = "meeting_has_action",
            joinColumns = @JoinColumn(name = "meeting"),
            inverseJoinColumns = @JoinColumn(name = "action")
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

    public Map getDailyRepetition() {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Map<String, Boolean> repetitionDays = new LinkedHashMap<>();
        if (dailyRepetition != null) {
            for (int i = 0; i < 7 && i < this.dailyRepetition.length(); i++) {
                boolean isRepeating = this.dailyRepetition.charAt(i) == '1';
                repetitionDays.put(daysOfWeek[i], isRepeating);
            }
            return repetitionDays;
        } else return null;
    }

}

