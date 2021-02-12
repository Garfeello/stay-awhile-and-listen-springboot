package org.backendComponents.StayAwhileAndListen.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Diablo2Quotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] quote;

    @ManyToOne
    private Diablo2Character diablo2Character;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Diablo2Character getDiablo2Character() {
        return diablo2Character;
    }

    public void setDiablo2Character(Diablo2Character diablo2Character) {
        this.diablo2Character = diablo2Character;
    }

    public byte[] getQuote() {
        return quote;
    }

    public void setQuote(byte[] quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Diablo2Quotes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quote=" + Arrays.toString(quote) +
                ", diablo2Character=" + diablo2Character +
                '}';
    }
}
