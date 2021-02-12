package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stayAwhileAndListen/character")
@CrossOrigin(origins = "http://localhost:3000")
public class CharacterController {

    private final Diablo2QuotesRepository diablo2QuotesRepository;

    public CharacterController(Diablo2QuotesRepository diablo2QuotesRepository) {
        this.diablo2QuotesRepository = diablo2QuotesRepository;
    }

    @ModelAttribute("diablo2AllQuotes")
    private List<Diablo2Quotes> diablo2Quotes() {
        return diablo2QuotesRepository.findAll().stream().sorted().collect(Collectors.toList());
    }

    @GetMapping("/test")
    private Diablo2Character test() {
        Diablo2Character diablo2Character = new Diablo2Character();
        diablo2Character.setDescription("description");
        diablo2Character.setName("TROLOLO");
        return diablo2Character;
    }
}