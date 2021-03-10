package org.backendComponents.StayAwhileAndListen.dto;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Diablo2QuoteDTO {

    private String name;
    private MultipartFile multipartFile;
    private Diablo2Character diablo2Character;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
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
                ", multipartFile=" + multipartFile +
                ", diablo2Character=" + diablo2Character +
                '}';
    }
}
