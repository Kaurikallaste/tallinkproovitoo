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
        try {
            return participantRepository.save(participant);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "participant could not be saved", ex);
        }
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
