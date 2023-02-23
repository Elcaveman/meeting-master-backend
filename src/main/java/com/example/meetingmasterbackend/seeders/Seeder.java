package com.example.meetingmasterbackend.seeders;

import com.example.meetingmasterbackend.models.*;
import com.example.meetingmasterbackend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

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
    public void run(String... args) throws Exception {
        // Profile
        Profile p1 = Profile.builder().name("Diaeddin BOUIDAINE").email("diaeddin@zsoft.com").build();
        profileRepository.save(p1);

        // Action and Meetings typess
        ActionType at1 = ActionType.builder().name("Action Type 1").build();
        ActionType at2 = ActionType.builder().name("Action Type 2").build();
        MeetingType mt1 = MeetingType.builder().name("Meeting Type 1").build();
        actionTypeRepository.saveAll(Arrays.asList(at1,at2));
        meetingTypeRepository.save(mt1);

        // Meeting
        Meeting m1 = Meeting.builder().name("Meeting1").beginsAt(new Date()).createdAt(new Date()).owner(p1)
                .isRepeated(true).repeatedEvery(3).endsAt(new Date()).isWeeklyRepeated(false).isMonthlyRepeated(true).type(mt1).dailyRepetition("1110100").build();
        meetingRepository.save(m1);

        // Action
        Action a1 = Action.builder().name("Action 1").createdAt(new Date()).owner(p1).deadline(new Date())
                .finishedByMeeting(m1).finishedByProfile(p1).type(at1).build();
        Action a2 = Action.builder().name("Action 2").createdAt(new Date()).owner(p1).deadline(new Date())
                .finishedByMeeting(m1).finishedByProfile(p1).type(at2).build();
        actionRepository.saveAll(Arrays.asList(a1,a2));

    }
}
