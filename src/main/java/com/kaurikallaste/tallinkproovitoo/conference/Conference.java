package com.kaurikallaste.tallinkproovitoo.conference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kaurikallaste.tallinkproovitoo.Participant.Participant;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CONFERENCE")
public class Conference {


    private @Id @GeneratedValue long id;
    private String name;
    private int maxParticipants;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference")
    @JsonManagedReference
    private List<Participant> participants = new ArrayList<>();

    public Conference(){}

    public Conference(Long id, String name, int maxParticipants){
        this.id = id;
        this.name = name;
        this.maxParticipants = maxParticipants;
    }


    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public boolean checkParticipantMax(){
        if(participants.size() < maxParticipants){
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "conference with id:" + id +  " participant limit reached");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", participants=" + participants +
                '}';
    }
}

