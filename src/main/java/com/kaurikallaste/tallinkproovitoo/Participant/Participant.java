package com.kaurikallaste.tallinkproovitoo.Participant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaurikallaste.tallinkproovitoo.conference.Conference;

import javax.persistence.*;

@Entity(name="PARTICIPANT")
public class Participant {


    private @Id @GeneratedValue Long id;
    private String name;

    @ManyToOne
    @JsonBackReference
    private Conference conference;

    public Participant(){}

    public Participant(Long id, String name, Conference conference){
        this.id = id;
        this.name = name;
        this.conference = conference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conference=" + conference +
                '}';
    }
}
