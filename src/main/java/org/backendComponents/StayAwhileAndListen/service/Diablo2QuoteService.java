package org.backendComponents.StayAwhileAndListen.service;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Diablo2QuoteService {

    private final Diablo2QuotesRepository diablo2QuotesRepository;
    private final Diablo2CharacterRepository diablo2CharacterRepository;

    public Diablo2QuoteService(Diablo2QuotesRepository diablo2QuotesRepository, Diablo2CharacterRepository diablo2CharacterRepository) {
        this.diablo2QuotesRepository = diablo2QuotesRepository;
        this.diablo2CharacterRepository = diablo2CharacterRepository;
    }

    public void ifQuoteExsistsThrowEx(Diablo2Quotes diablo2Quote) {
        if (diablo2QuotesRepository.findFirstByName(diablo2Quote.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "D2 quote already exsists");
        }
    }

    public Diablo2Quotes findDiablo2QuoteOrThrowEx(Long id) {
        return diablo2QuotesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 quote not found"));
    }

    public Diablo2Quotes setQuoteCharacterIfExsists(Diablo2Quotes diablo2Quote) {
        diablo2Quote.setDiablo2Character(diablo2CharacterRepository.findFirstByName(diablo2Quote.getDiablo2Character().getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 Character not found")));
        return diablo2Quote;
    }
}