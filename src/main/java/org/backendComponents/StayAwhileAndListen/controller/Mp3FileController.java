package org.backendComponents.StayAwhileAndListen.controller;

import org.backendComponents.StayAwhileAndListen.repository.Diablo2QuotesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/stayAwhileAndListen/audio")
@CrossOrigin(origins = "http://localhost:3000")
public class Mp3FileController {

    private final Diablo2QuotesRepository quotesRepository;

    public Mp3FileController(Diablo2QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/getById/{id}")
    private void bytes(HttpServletResponse response, @PathVariable Long id) {
        try {
            OutputStream o = response.getOutputStream();
            o.write(quotesRepository.getOne(id).getQuote());
            response.setContentType("audio/mpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
