package org.StayAwhileAndListen.dto;

import org.StayAwhileAndListen.model.Diablo2Character;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Diablo2QuoteDTO {

    private String name;
    private MultipartFile quote;
    private Diablo2Character diablo2Character;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getQuote() {
        return quote;
    }

    public void setQuote(MultipartFile quote) {
        this.quote = quote;
    }

    public Diablo2Character getDiablo2Character() {
        return diablo2Character;
    }

    public void setDiablo2Character(Diablo2Character diablo2Character) {
        this.diablo2Character = diablo2Character;
    }

    @Override
    public String toString() {
        return "Diablo2QuoteDTO{" +
                "name='" + name + '\'' +
                ", quote=" + quote +
                ", diablo2Character=" + diablo2Character +
                '}';
    }
}
