package org.backendComponents.StayAwhileAndListen.service;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Diablo2CharacterService {

    private final Diablo2CharacterRepository diablo2CharacterRepository;

    public Diablo2CharacterService(Diablo2CharacterRepository diablo2CharacterRepository) {
        this.diablo2CharacterRepository = diablo2CharacterRepository;
    }

    public void ifCharacterExsistsThrowEx(Diablo2Character diablo2Character) {
        if (diablo2CharacterRepository.findFirstByName(diablo2Character.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "D2 character already exsists");
        }
    }

    public Diablo2Character findDiablo2CharacterOrThrowEx(Long id) {
        return diablo2CharacterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 character not found"));
    }

}