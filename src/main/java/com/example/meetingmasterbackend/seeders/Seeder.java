//package com.example.meetingmasterbackend.seeders;
//
//import com.example.meetingmasterbackend.models.Action;
//import com.example.meetingmasterbackend.repositories.ActionRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Seeder implements CommandLineRunner {
//    private final ActionRepository actionRepository;
//
//    public Seeder(ActionRepository actionRepository) {
//        this.actionRepository = actionRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        Action a1 = new Action();
//        Action a2 = new Action();
//        Action a3 = new Action();
//        Action a4 = new Action();
//        actionRepository.save(a1);actionRepository.save(a2);actionRepository.save(a3);actionRepository.save(a4);
//    }
//}
