package org.backendComponents.StayAwhileAndListen.model;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Diablo2Character diablo2Character;

    private LocalDate daily;

    private Boolean favourite;

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

    public byte[] getQuote() {
        return quote;
    }

    public void setQuote(byte[] quote) {
        this.quote = quote;
    }

    public Diablo2Character getDiablo2Character() {
        return diablo2Character;
    }

    public void setDiablo2Character(Diablo2Character diablo2Character) {
        this.diablo2Character = diablo2Character;
    }

    public LocalDate getDaily() {
        return daily;
    }

    public void setDaily(LocalDate daily) {
        this.daily = daily;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Diablo2Quotes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quote=" + Arrays.toString(quote) +
                ", diablo2Character=" + diablo2Character +
                ", daily=" + daily +
                ", favourite=" + favourite +
                '}';
    }
}
