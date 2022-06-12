package com.kaurikallaste.tallinkproovitoo.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    ConferenceRepository conferenceRepository;

    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    public Conference findConferenceById(Long id){
        return conferenceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "conference with id: " + id + " not found"));
    }

    public Conference addConference(Conference conference) {
        if(conferenceRepository.existsById(conference.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "conference with that id already exists");
        }
        return conferenceRepository.save(conference);
    }

    public void deleteConference(Long id){
        if(conferenceRepository.existsById(id)){
            conferenceRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "conference with id: " + id + " not found");
        }
    }

    public boolean checkParticipantMax(Long id) {
        return findConferenceById(id).checkParticipantMax();
    }
}
