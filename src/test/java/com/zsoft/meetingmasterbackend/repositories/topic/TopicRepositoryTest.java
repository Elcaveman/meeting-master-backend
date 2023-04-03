//package com.zsoft.meetingmasterbackend.repositories.topic;
//
//import com.zsoft.meetingmasterbackend.models.Meeting;
//import com.zsoft.meetingmasterbackend.models.Topic;
//import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
//import com.zsoft.meetingmasterbackend.repositories.TopicRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.HashSet;
//import java.util.List;
//
//@DataJpaTest
//public class TopicRepositoryTest {
//    @Autowired
//    private TopicRepository topicRepository;
//
//    @Test
//    public void TestFindTopicsByMeetingId(){
//        Meeting meeting = Meeting.builder().name("meeting1").build();
//        Topic topic = Topic.builder().meetings(new HashSet<>()).name("topic1").build();
//        topic.getMeetings().add(meeting);
//
//        Topic savedTopic = topicRepository.save(topic);
//    }
//
//
//}
