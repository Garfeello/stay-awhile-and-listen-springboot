package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.dto.Diablo2QuoteDTO;
import org.backendComponents.StayAwhileAndListen.model.Diablo2Quote;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.backendComponents.StayAwhileAndListen.service.Diablo2QuoteService;
import org.backendComponents.StayAwhileAndListen.service.Mp3SaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/allQuotes")
    private List<Diablo2Quote> getAllQuotes() {
        return quotesRepository.findAll();
    }

    @PostMapping("/addQuote")
    private Diablo2Quote addQuote(@RequestBody Diablo2QuoteDTO diablo2QuoteDTO) {
        quoteService.ifQuoteExsistsThrowEx(diablo2QuoteDTO.getMultipartFile().getName());
        Diablo2Quote diablo2Quote = new Diablo2Quote();
        diablo2Quote.setName(diablo2QuoteDTO.getMultipartFile().getName());
        diablo2Quote.setQuote(mp3SaveService.getMp3File(diablo2QuoteDTO.getMultipartFile()));
        diablo2Quote.setDiablo2Character(quoteService.setQuoteCharacterIfExsists(diablo2QuoteDTO.getDiablo2Character()));
        return quotesRepository.save(diablo2Quote);
    }

    @GetMapping("/getQuote/{name}")
    private Diablo2Quote getQuoteByName(@PathVariable String name) {
        return quotesRepository.findFirstByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 quote not found"));
    }

    @PutMapping("/editQuote/{id}")
    private Diablo2Quote updateDiablo2Quote(@RequestBody Diablo2Quote diablo2Quote, @PathVariable Long id) {
        Diablo2Quote currentQuote = quoteService.findDiablo2QuoteOrThrowEx(id);
        currentQuote.setName(diablo2Quote.getName());
        currentQuote.setQuote(diablo2Quote.getQuote());
        currentQuote.setDiablo2Character(diablo2Quote.getDiablo2Character());
        return quotesRepository.save(currentQuote);
    }

    @DeleteMapping("/deleteQuote/{id}")
    private ResponseEntity<String> removeQuoteById(@PathVariable Long id) {
        quotesRepository.deleteById(quoteService.findDiablo2QuoteOrThrowEx(id).getId());
        return ResponseEntity.ok("Deleted quote with id: " + id);
    }
}