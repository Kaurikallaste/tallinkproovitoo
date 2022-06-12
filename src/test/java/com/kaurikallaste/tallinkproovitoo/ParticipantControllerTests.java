package com.kaurikallaste.tallinkproovitoo;

import com.kaurikallaste.tallinkproovitoo.Participant.Participant;
import com.kaurikallaste.tallinkproovitoo.Participant.ParticipantController;
import com.kaurikallaste.tallinkproovitoo.Participant.ParticipantService;
import com.kaurikallaste.tallinkproovitoo.Conference.Conference;
import com.kaurikallaste.tallinkproovitoo.Conference.ConferenceService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ParticipantController.class)
public class ParticipantControllerTests {

    @MockBean
    ConferenceService conferenceService;

    @MockBean
    ParticipantService participantService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldAddParticipant(){
        Conference conference = new Conference(1L, "testConference", 50);
        Participant participant = new Participant(3L, "testParticipant", conference);

        Mockito.when(conferenceService.findConferenceById(conference.getId())).thenReturn(conference);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(participant)
                .when()
                .post("/participants/" + conference.getId())
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldNotAddParticipantWithBlankName(){
        Conference conference = new Conference(1L, "testConference", 50);
        Participant participant = new Participant(3L, "", conference);

        Mockito.when(conferenceService.findConferenceById(conference.getId())).thenReturn(conference);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(participant)
                .when()
                .post("/participants/" + conference.getId())
                .then()
                .statusCode(400);
    }

}
