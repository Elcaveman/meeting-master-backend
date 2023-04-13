//package com.zsoft.meetingmasterbackend.controllers.meeting;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zsoft.meetingmasterbackend.controllers.MeetingController;
//import com.zsoft.meetingmasterbackend.dto.meeting.MeetingCreateDTO;
//import com.zsoft.meetingmasterbackend.dto.meeting.MeetingTypeDTO;
//import com.zsoft.meetingmasterbackend.dto.meeting.SimpleMeetingDTO;
//import com.zsoft.meetingmasterbackend.dto.profile.ProfileDTO;
//import com.zsoft.meetingmasterbackend.mappers.MeetingMapper;
//import com.zsoft.meetingmasterbackend.models.Meeting;
//import com.zsoft.meetingmasterbackend.models.MeetingType;
//import com.zsoft.meetingmasterbackend.models.Profile;
//import com.zsoft.meetingmasterbackend.repositories.MeetingRepository;
//import com.zsoft.meetingmasterbackend.repositories.MeetingTypeRepository;
//import com.zsoft.meetingmasterbackend.repositories.ProfileRepository;
//import com.zsoft.meetingmasterbackend.services.MeetingService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(MeetingController.class)
//@AutoConfigureMockMvc
//public class MeetingControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MeetingService meetingService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private MeetingRepository meetingRepository;
//
//
//    @MockBean
//    private ProfileRepository profileRepository;
//
//    @MockBean
//    private MeetingTypeRepository meetingTypeRepository;
//
//    @Test
//    void createMeeting() throws Exception{
//        // Create Profile
//        Profile profile = new Profile();
//        profile.setName("TestOwner");
//        profile.setId(1L);
//
//        //Create Meeting Type
//        MeetingType meetingType = new MeetingType();
//        meetingType.setId(1L);
//        meetingType.setName("MeetingTypeTest");
//
//        // Needed assumptions
//        given(profileRepository.findProfileById(1L)).willReturn(profile);
//        given(meetingTypeRepository.findMeetingTypeById(1L)).willReturn(meetingType);
//
//        // Test
//        MeetingCreateDTO meetingCreateDTO = new MeetingCreateDTO();
//        meetingCreateDTO.setId(1L);
//        meetingCreateDTO.setOwner(1L);
//        meetingCreateDTO.setType(1L);
//        meetingCreateDTO.setName("TestMeeting");
//        String json_meeting = objectMapper.writeValueAsString(meetingCreateDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json_meeting))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name",is("TestMeeting")));
//
//    }
//}
