package com.kaurikallaste.tallinkproovitoo;

import com.kaurikallaste.tallinkproovitoo.Conference.Conference;
import com.kaurikallaste.tallinkproovitoo.Conference.ConferenceController;
import com.kaurikallaste.tallinkproovitoo.Conference.ConferenceService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ConferenceController.class)
public class ConferenceControllerTests {

    @MockBean
    ConferenceService conferenceService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void ShouldReturnAllConferences(){
        Conference conferenceOne = new Conference(1L, "test", 3);
        Conference conferenceTwo = new Conference(2L, "test2", 4);

        Mockito.when(conferenceService.getAllConferences()).thenReturn(
                List.of(conferenceOne, conferenceTwo));

        RestAssuredMockMvc.given()
                .when()
                    .get("/conferences")
                .then()
                    .statusCode(200)
                    .body("$.size()", Matchers.equalTo(2),
                            "[0].id", Matchers.equalTo((int) conferenceOne.getId()),
                            "[0].name", Matchers.equalTo(conferenceOne.getName()),
                            "[0].maxParticipants", Matchers.equalTo(conferenceOne.getMaxParticipants()),
                            "[1].id", Matchers.equalTo((int) conferenceTwo.getId()),
                            "[1].name", Matchers.equalTo(conferenceTwo.getName()),
                            "[1].maxParticipants", Matchers.equalTo(conferenceTwo.getMaxParticipants())
                    );
    }

    @Test
    public void ShouldReturnConferenceWithId(){
        Conference conference = new Conference(1L, "test", 3);

        Mockito.when(conferenceService.findConferenceById(conference.getId())).thenReturn(conference);

        RestAssuredMockMvc.given()
                .when()
                    .get("/conferences/" + conference.getId())
                .then()
                    .statusCode(200)
                    .body("id", Matchers.equalTo((int) conference.getId()),
                        "name", Matchers.equalTo(conference.getName()),
                        "maxParticipants", Matchers.equalTo(conference.getMaxParticipants())
                );
    }

    @Test
    public void shouldAddController(){
        Conference conference = new Conference(1L, "test", 3);

        RestAssuredMockMvc.given()
                    .contentType("application/json")
                    .body(conference)
                .when()
                    .post("/conferences")
                .then()
                    .statusCode(201);
    }

    @Test
    public void shouldNotAddControllerWithBlankName(){
        Conference conference = new Conference(1L, "", 3);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(conference)
                .when()
                .post("/conferences")
                .then()
                .statusCode(400);
    }

    @Test
    public void shouldNotAddControllerWithNegativeMaxParticipants(){
        Conference conference = new Conference(1L, "test", -2);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(conference)
                .when()
                .post("/conferences")
                .then()
                .statusCode(400);
    }
    
}
