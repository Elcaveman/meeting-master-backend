package com.zsoft.meetingmasterbackend.seeders;

import com.zsoft.meetingmasterbackend.models.*;
import com.zsoft.meetingmasterbackend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Seeder implements CommandLineRunner {
    private final ActionRepository actionRepository;
    private final ProfileRepository profileRepository;
    private final MeetingRepository meetingRepository;
    private final ActionTypeRepository actionTypeRepository;
    private final MeetingTypeRepository meetingTypeRepository;

    public Seeder(ActionRepository actionRepository, ProfileRepository profileRepository,MeetingRepository meetingRepository, ActionTypeRepository actionTypeRepository, MeetingTypeRepository meetingTypeRepository) {
        this.actionRepository = actionRepository;
        this.profileRepository = profileRepository;
        this.meetingRepository = meetingRepository;
        this.actionTypeRepository = actionTypeRepository;
        this.meetingTypeRepository = meetingTypeRepository;
    }

    @Override
    @Transactional
    public void run(String... args){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        // Profile
        Profile p1 = Profile.builder().name("Diaeddin BOUIDAINE").email("diaeddin@zsoft.com").build();
        Profile p2 = Profile.builder().name("Mehdi Oudaoud").email("mehdi@zsoft.com").build();
        profileRepository.saveAll(Arrays.asList(p1,p2));

        // Action and Meetings types
        ActionType at1 = ActionType.builder().name("Action Type 1").build();
        ActionType at2 = ActionType.builder().name("Action Type 2").build();
        MeetingType mt1 = MeetingType.builder().name("Meeting Type 1").build();
        actionTypeRepository.saveAll(Arrays.asList(at1,at2));
        meetingTypeRepository.save(mt1);

        // Meeting
        Meeting m1 = Meeting.builder().name("Meeting1").beginsAt(new Date()).createdAt(new Date()).owner(p1)
             .endsAt(new Date()).weekRepetition(1).monthRepetition(0).type(mt1).dailyRepetition("0001111").build();
        Meeting m2 = Meeting.builder().name("Meeting2").beginsAt(new Date()).createdAt(new Date()).owner(p2)
                .endsAt(new Date("20/12/2023")).weekRepetition(0).monthRepetition(4).type(mt1).build();
        meetingRepository.saveAll(Arrays.asList(m1,m2));

        // Action
        Action a1 = Action.builder().name("Action 1").createdAt(new Date()).owner(p1).deadline(new Date())
                .finishedByMeeting(m1).finishedByProfile(p1).type(at1).build();
        Action a2 = Action.builder().name("Action 2").createdAt(new Date()).owner(p1).deadline(new Date())
                .finishedByMeeting(m1).finishedByProfile(p1).type(at2).build();
//        Action a3 = Action.builder().name("Action 3").createdAt(new Date("2022-10-16")).owner(p2).deadline(new Date("2022-10-20")).build();
        actionRepository.saveAll(Arrays.asList(a1,a2));
    }
}
