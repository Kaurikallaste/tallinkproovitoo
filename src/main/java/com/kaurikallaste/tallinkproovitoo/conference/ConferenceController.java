package com.kaurikallaste.tallinkproovitoo.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class ConferenceController {

    @Autowired
    ConferenceService conferenceService;


    @GetMapping("/conferences")
    public List<Conference> getConferences(){
        return conferenceService.getAllConferences();
    }

    @GetMapping("/conferences/{id}")
    public Conference getConference(@PathVariable Long id){
        return conferenceService.findConferenceById(id);
    }

    @GetMapping("/conferences/{id}/max_participants")
    public boolean checkParticipantMax(@PathVariable Long id){
        return conferenceService.checkParticipantMax(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/conferences")
    public Conference addConference(@Valid @RequestBody Conference conference){
        return conferenceService.addConference(conference);
    }

    @DeleteMapping("/conferences/{id}")
    public void deleteConference(@PathVariable Long id){
        conferenceService.deleteConference(id);
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
