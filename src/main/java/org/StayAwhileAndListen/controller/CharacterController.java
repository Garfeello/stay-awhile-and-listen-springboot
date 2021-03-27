package org.StayAwhileAndListen.controller;

import org.StayAwhileAndListen.service.Diablo2CharacterService;
import org.StayAwhileAndListen.model.Diablo2Character;
import org.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stayAwhileAndListen/character")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.27:3000"})
public class CharacterController {

    private final Diablo2CharacterRepository characterRepository;
    private final Diablo2CharacterService characterService;

    public CharacterController(Diablo2CharacterRepository diablo2CharacterRepository, Diablo2CharacterService diablo2CharacterService) {
        this.characterRepository = diablo2CharacterRepository;
        this.characterService = diablo2CharacterService;
    }

    @GetMapping("/allCharacters")
    private List<Diablo2Character> getAllCharacters() {
        return characterRepository.findAll().stream()
                .sorted((Comparator.comparing(Diablo2Character::getName)))
                .collect(Collectors.toList());
    }

    @PostMapping("/addCharacter")
    private Diablo2Character addQuote(@RequestBody Diablo2Character diablo2Character) {
        characterService.ifCharacterExsistsThrowEx(diablo2Character);
        return characterRepository.save(diablo2Character);
    }

    @GetMapping("/getCharacter/{characterId}")
    private Diablo2Character getQuoteByName(@PathVariable long characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 character not found"));
    }

    @PutMapping("/editCharacter")
    private Diablo2Character updateDiablo2Quote(@RequestBody Diablo2Character diablo2Character) {
        Diablo2Character currentCharacter = characterService.findDiablo2CharacterOrThrowEx(diablo2Character.getId());
        currentCharacter.setName(diablo2Character.getName());
        currentCharacter.setDescription(diablo2Character.getDescription());
        return characterRepository.save(currentCharacter);
    }

    @DeleteMapping("/deleteCharacter/{id}")
    private ResponseEntity<String> removeQuoteById(@PathVariable Long id) {
        characterRepository.deleteById(characterService.findDiablo2CharacterOrThrowEx(id).getId());
        return ResponseEntity.ok("Deleted quote with id: " + id);
    }
}