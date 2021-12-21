package com.fedormamaevv.SpringProjectTemp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ParticipantController {
    private final ParticipantRepository participantRepository;

    public ParticipantController(ParticipantRepository repository) {
        this.participantRepository = repository;
    }

    @GetMapping("api/participants")
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @GetMapping("api/participants/{id}")
    public Participant getParticipant(@PathVariable Long id)
            throws NotFoundException {
        Optional<Participant> partData = participantRepository.findById(id);
        if (partData.isEmpty()) throw new NotFoundException();
        return partData.get();
    }

    @PostMapping("api/participants")
    public Participant addParticipant(@RequestBody Participant participant) {
        return participantRepository.save(new Participant(participant.getFirstName(),
                participant.getLastName(),
                participant.getAge(),
                participant.getPhoneNumber(),
                participant.getEmailAddress(),
                participant.getCountry(),
                participant.getRegion(),
                participant.getCity(),
                participant.getSchool(),
                participant.getGrade()));
    }

    @PutMapping("api/participants/{id}")
    public Participant updateParticipant(@RequestBody Participant participant,
                                         @PathVariable Long id)
        throws NotFoundException {
        Optional<Participant> partData = participantRepository.findById(id);
        if (partData.isEmpty())
            throw new NotFoundException();
        Participant new_participant = partData.get();
        new_participant.setAge(participant.getAge());
        new_participant.setCity(participant.getCity());
        new_participant.setCountry(participant.getCountry());
        new_participant.setEmailAddress(participant.getEmailAddress());
        new_participant.setFirstName(participant.getFirstName());
        new_participant.setGrade(participant.getGrade());
        new_participant.setLastName(participant.getLastName());
        new_participant.setPhoneNumber(participant.getPhoneNumber());
        new_participant.setRegion(participant.getRegion());
        new_participant.setSchool(participant.getSchool());
        return participantRepository.save(new_participant);
    }

    @DeleteMapping("api/participants/{id}")
    public HttpStatus removeParticipant(@PathVariable Long id)
        throws NotFoundException {
        if (participantRepository.findById(id).isEmpty())
            throw new NotFoundException();
        participantRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
