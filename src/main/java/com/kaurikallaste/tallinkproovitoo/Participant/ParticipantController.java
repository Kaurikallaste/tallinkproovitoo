package com.kaurikallaste.tallinkproovitoo.Participant;

import com.kaurikallaste.tallinkproovitoo.Conference.Conference;
import com.kaurikallaste.tallinkproovitoo.Conference.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @Autowired
    ConferenceService conferenceService;

    @DeleteMapping("/participants/{id}")
    public void deleteParticipant(@PathVariable Long id){
        participantService.deleteParticipant(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/participants/{id}")
    public Participant addParticipant(@PathVariable Long id,@Valid @RequestBody Participant participant){
        Conference conference = conferenceService.findConferenceById(id);

        //check if conference has room for more participants
        if(conference.checkParticipantMax()){
            //set conference to the participant based on provided conference id
            participant.setConference(conference);
        }
        return participantService.addParticipant(participant);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
