package com.kaurikallaste.tallinkproovitoo.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    public Participant addParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id){
        if(participantRepository.existsById(id)){
            participantRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "participant with id: " + id + " not found");
        }
    }

    public Participant findParticipantById(Long id) {
        return participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "participant with id: " + id + " not found"));
    }
}
