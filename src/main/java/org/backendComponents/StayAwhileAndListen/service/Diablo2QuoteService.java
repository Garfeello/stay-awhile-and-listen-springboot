package org.backendComponents.StayAwhileAndListen.service;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2CharacterRepository;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class Diablo2QuoteService {

    private final Diablo2QuotesRepository diablo2QuotesRepository;
    private final Diablo2CharacterRepository diablo2CharacterRepository;

    public Diablo2QuoteService(Diablo2QuotesRepository diablo2QuotesRepository, Diablo2CharacterRepository diablo2CharacterRepository) {
        this.diablo2QuotesRepository = diablo2QuotesRepository;
        this.diablo2CharacterRepository = diablo2CharacterRepository;
    }

    public void ifQuoteMpegExsistsThrowEx(String name) {
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

    public Diablo2Quotes getDailyQuote(int dayInformation) {
        LocalDate localDate = LocalDate.now();
        Optional<Diablo2Quotes> firstByDaily = diablo2QuotesRepository.findFirstByDaily(LocalDate.of(localDate.getYear(), localDate.getMonth(), dayInformation));
        if (firstByDaily.isPresent()) {
            return firstByDaily.get();
        } else {
            Diablo2Quotes diablo2Quotes = getRandomQuote();
            diablo2Quotes.setDaily(localDate);
            return diablo2QuotesRepository.save(diablo2Quotes);
        }
    }

    public Diablo2Quotes getRandomQuote() {
        return diablo2QuotesRepository.getRandomQuote().orElse(new Diablo2Quotes());
    }
}