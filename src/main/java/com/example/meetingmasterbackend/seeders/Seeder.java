package com.example.meetingmasterbackend.seeders;

import com.example.meetingmasterbackend.models.*;
import com.example.meetingmasterbackend.repositories.ActionRepository;
import com.example.meetingmasterbackend.repositories.MeetingRepository;
import com.example.meetingmasterbackend.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Seeder implements CommandLineRunner {
    private final ActionRepository actionRepository;
    private final ProfileRepository profileRepository;
    private final MeetingRepository meetingRepository;

    public Seeder(ActionRepository actionRepository, ProfileRepository profileRepository,MeetingRepository meetingRepository) {
        this.actionRepository = actionRepository;
        this.profileRepository = profileRepository;
        this.meetingRepository = meetingRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Profile
        Profile p1 = new Profile();
        p1.setName("Diaeddin BOUIDAINE");p1.setEmail("bouidaine@gmail.com");
        profileRepository.save(p1);
        // Action Type
        //        ActionType at1 = new ActionType();at1.setName("ActionType1");
        //        Meeting Type
        //        MeetingType mt1 = new MeetingType();mt1.setName("MeetingType1");

        // Meeting
        Meeting m1 = new Meeting();m1.setName("Meeting1");m1.setBeginsAt(new Date());
        m1.setCreatedAt(new Date());m1.setOwner(p1);m1.setTime("13:15");m1.setRepeated(true);
        m1.setEndsAt(new Date());m1.setRepeatedEvery(3);m1.setWeeklyRepeated(true);
        meetingRepository.save(m1);
        // Action
        Action a1 = new Action();
        a1.setName("Action 1");a1.setCreatedAt(new Date());a1.setOwner(p1);a1.setDeadline(new Date());
        a1.setFinishedByProfile(p1);a1.setFinishedByMeeting(m1);a1.getMeetings().add(m1);
        actionRepository.save(a1);
        Action a2 = Action.builder().name("Action 2").createdAt(new Date()).owner(p1).deadline(new Date())
                .finishedByMeeting(m1).finishedByProfile(p1).build();
        actionRepository.save(a2);

    }
}
