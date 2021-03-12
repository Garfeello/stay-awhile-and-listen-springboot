package org.backendComponents.StayAwhileAndListen.service;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class Diablo2QuoteService {

    private final Diablo2QuotesRepository diablo2QuotesRepository;
    private final Diablo2CharacterRepository diablo2CharacterRepository;

    public Diablo2QuoteService(Diablo2QuotesRepository diablo2QuotesRepository, Diablo2CharacterRepository diablo2CharacterRepository) {
        this.diablo2QuotesRepository = diablo2QuotesRepository;
        this.diablo2CharacterRepository = diablo2CharacterRepository;
    }

    public void ifQuoteExsistsThrowEx(String name) {
        if (diablo2QuotesRepository.findFirstByName(name).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "D2 quote already exsists");
        }
    }

    public Diablo2Quotes findDiablo2QuoteOrThrowEx(Long id) {
        return diablo2QuotesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 quote not found"));
    }

    public Diablo2Character setQuoteIfCharacterExsists(String characterName) {
        return diablo2CharacterRepository
                .findFirstByName(characterName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 Character not found"));
    }

    public Diablo2Quotes getRandomQuoteIfNewDay(int dayInformation) {
        int currentDay = 0;
        Diablo2Quotes diablo2Quotes = new Diablo2Quotes();
        if (currentDay != dayInformation){
            diablo2Quotes = getRandomQuote();
        }
        return diablo2Quotes;
    }

    private Diablo2Quotes getRandomQuote(){
        List<Diablo2Quotes> allQuotes = diablo2QuotesRepository.getAllQuotes().orElse(Collections.emptyList());
        return allQuotes.get((int) (Math.random() * allQuotes.size()));
    }
}