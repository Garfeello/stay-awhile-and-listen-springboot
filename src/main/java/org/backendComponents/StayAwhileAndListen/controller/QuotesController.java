package org.backendComponents.StayAwhileAndListen.controller;


import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stayAwhileAndListen/quotes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuotesController {

    private final Diablo2QuotesRepository diablo2QuotesRepository;
    private final Diablo2CharacterRepository diablo2Character;

    public QuotesController(Diablo2QuotesRepository diablo2QuotesRepository, Diablo2CharacterRepository diablo2Character) {
        this.diablo2QuotesRepository = diablo2QuotesRepository;
        this.diablo2Character = diablo2Character;
    }


    @GetMapping("/getQuoteById/{id}")
    private Diablo2Quotes getQuoteById(@PathVariable Long id) {
        return diablo2QuotesRepository.findById(id).orElse(new Diablo2Quotes());

    }

    @PostMapping("/addQuote")
    private Diablo2Quotes addQuote(@RequestBody Diablo2Quotes diablo2Quotes, @RequestParam String characterName) {
        diablo2Quotes.setDiablo2Character(diablo2Character.findDiablo2CharacterByName(characterName).orElse(new Diablo2Character()));
        return diablo2QuotesRepository.save(diablo2Quotes);
    }

    @DeleteMapping("/deleteQuote/{id}")
    private String removeQuoteById(@PathVariable Long id) {
        diablo2QuotesRepository.deleteById(id);
        return "Removed quote with id: " + id;
    }

    @PutMapping("/editQuote")
    private Diablo2Quotes updateDiablo2Quote(@RequestBody Diablo2Quotes diablo2Quotes) {
        return diablo2QuotesRepository.save(diablo2Quotes);
    }


}
