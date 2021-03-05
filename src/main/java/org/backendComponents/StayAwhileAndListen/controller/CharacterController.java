package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.backendComponents.StayAwhileAndListen.service.Diablo2CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stayAwhileAndListen/character")
@CrossOrigin(origins = "http://localhost:3000")
public class CharacterController {

    private final Diablo2CharacterRepository characterRepository;
    private final Diablo2CharacterService characterService;

    public CharacterController(Diablo2CharacterRepository diablo2CharacterRepository, Diablo2CharacterService diablo2CharacterService) {
        this.characterRepository = diablo2CharacterRepository;
        this.characterService = diablo2CharacterService;
    }

    @GetMapping("/allCharacters")
    private List<Diablo2Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @PostMapping("/addCharacter")
    private Diablo2Character addQuote(@RequestBody Diablo2Character diablo2Character) {
        characterService.ifCharacterExsistsThrowEx(diablo2Character);
        return characterRepository.save(diablo2Character);
    }

    @GetMapping("/getCharacter/{name}")
    private Diablo2Character getQuoteByName(@PathVariable String name) {
        return characterRepository.findFirstByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 character not found"));
    }

    @PutMapping("/editCharacter/{id}")
    private Diablo2Character updateDiablo2Quote(@RequestBody Diablo2Character diablo2Character, @PathVariable Long id) {
        Diablo2Character currentCharacter = characterService.findDiablo2CharacterOrThrowEx(id);
        currentCharacter.setName(diablo2Character.getName());
        currentCharacter.setDescription(diablo2Character.getDescription());
        currentCharacter.setDiablo2Quotes(diablo2Character.getDiablo2Quotes());
        return characterRepository.save(currentCharacter);
    }

    @DeleteMapping("/deleteCharacter/{id}")
    private ResponseEntity<String> removeQuoteById(@PathVariable Long id) {
        characterRepository.deleteById(characterService.findDiablo2CharacterOrThrowEx(id).getId());
        return ResponseEntity.ok("Deleted quote with id: " + id);
    }
}