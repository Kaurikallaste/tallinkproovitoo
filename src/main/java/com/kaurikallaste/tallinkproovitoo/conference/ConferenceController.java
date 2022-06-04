package com.kaurikallaste.tallinkproovitoo.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public Conference addConference(@RequestBody Conference conference){
        return conferenceService.addConference(conference);
    }

    @DeleteMapping("/conferences/{id}")
    public void deleteConference(@PathVariable Long id){
        conferenceService.deleteConference(id);
    }
}
