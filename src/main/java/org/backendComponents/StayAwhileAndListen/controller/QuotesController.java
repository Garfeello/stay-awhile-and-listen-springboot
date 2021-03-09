package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.backendComponents.StayAwhileAndListen.service.Diablo2QuoteService;
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

    public QuotesController(Diablo2QuotesRepository quotesRepository, Diablo2QuoteService diablo2QuoteService) {
        this.quotesRepository = quotesRepository;
        this.quoteService = diablo2QuoteService;
    }

    @GetMapping("/allQuotes")
    private List<Diablo2Quotes> getAllQuotes() {
        return quotesRepository.findAll();
    }

    @PostMapping("/addQuote")
    private Diablo2Quotes addQuote(@RequestBody Diablo2Quotes diablo2Quote) {
        quoteService.ifQuoteExsistsThrowEx(diablo2Quote);
        return quotesRepository.save(quoteService.setQuoteCharacterIfExsists(diablo2Quote));
    }

    @GetMapping("/getQuote/{name}")
    private Diablo2Quotes getQuoteByName(@PathVariable String name) {
        return quotesRepository.findFirstByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "D2 quote not found"));
    }

    @PutMapping("/editQuote/{id}")
    private Diablo2Quotes updateDiablo2Quote(@RequestBody Diablo2Quotes diablo2Quotes, @PathVariable Long id) {
        Diablo2Quotes currentQuote = quoteService.findDiablo2QuoteOrThrowEx(id);
        currentQuote.setName(diablo2Quotes.getName());
        currentQuote.setQuote(diablo2Quotes.getQuote());
        currentQuote.setDiablo2Character(diablo2Quotes.getDiablo2Character());
        return quotesRepository.save(currentQuote);
    }

    @DeleteMapping("/deleteQuote/{id}")
    private ResponseEntity<String> removeQuoteById(@PathVariable Long id) {
        quotesRepository.deleteById(quoteService.findDiablo2QuoteOrThrowEx(id).getId());
        return ResponseEntity.ok("Deleted quote with id: " + id);
    }
}