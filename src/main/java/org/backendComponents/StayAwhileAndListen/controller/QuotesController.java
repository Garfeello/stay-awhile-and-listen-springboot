package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.backendComponents.StayAwhileAndListen.service.Diablo2QuoteService;
import org.backendComponents.StayAwhileAndListen.service.Mp3SaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stayAwhileAndListen/quotes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuotesController {

    private final Diablo2QuotesRepository quotesRepository;
    private final Diablo2QuoteService quoteService;
    private final Mp3SaveService mp3SaveService;

    public QuotesController(Diablo2QuotesRepository quotesRepository, Diablo2QuoteService diablo2QuoteService, Mp3SaveService mp3SaveService) {
        this.quotesRepository = quotesRepository;
        this.quoteService = diablo2QuoteService;
        this.mp3SaveService = mp3SaveService;
    }

    @GetMapping("/randomQuote")
    private Diablo2Quotes getRandomQuote(){
        return quoteService.getRandomQuote();
    }

    @GetMapping("/getRandomDailyQuote/{currentDayInformation}")
    private Diablo2Quotes getRandomQuoteForDay(@PathVariable int currentDayInformation) {
        return quoteService.getDailyQuote(currentDayInformation);
    }

    @GetMapping("/allQuotes")
    private List<Diablo2Quotes> getAllQuotes() {
        return quotesRepository.getAllQuotes().orElse(Collections.emptyList());
    }

    @GetMapping("/setFavouriteQuote/{id}")
    private void setFavouriteQuote(@PathVariable Long id){
        Diablo2Quotes diablo2Quotes = quoteService.findDiablo2QuoteOrThrowEx(id);
        diablo2Quotes.setFavourite(!diablo2Quotes.getFavourite());
        quotesRepository.save(diablo2Quotes);
    }

    //CRUD
    @PostMapping(value = "/addQuote")
    private Diablo2Quotes test(@RequestParam MultipartFile mpegFile, @RequestParam String characterName) {
        quoteService.ifQuoteMpegExsistsThrowEx(mpegFile.getOriginalFilename());
        Diablo2Quotes diablo2Quote = new Diablo2Quotes();
        diablo2Quote.setName(mpegFile.getOriginalFilename());
        diablo2Quote.setQuote(mp3SaveService.getMpegBlob(mpegFile));
        diablo2Quote.setDiablo2Character(quoteService.setQuoteIfCharacterExsists(characterName));
        diablo2Quote.setFavourite(false);
        return quotesRepository.save(diablo2Quote);
    }

    @GetMapping("/getQuote/{id}")
    private Diablo2Quotes getQuoteByName(@PathVariable Long id) {
        return quotesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 quote not found"));
    }

    // in this case edit is quite redundant

    @DeleteMapping("/deleteQuote/{id}")
    private ResponseEntity<String> removeQuoteById(@PathVariable Long id) {
        quotesRepository.deleteById(quoteService.findDiablo2QuoteOrThrowEx(id).getId());
        return ResponseEntity.ok("Deleted quote with id: " + id);
    }
}