package org.backendComponents.StayAwhileAndListen.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diablo2Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany
    private List<Diablo2Quotes> diablo2Quotes;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Diablo2Quotes> getDiablo2Quotes() {
        return diablo2Quotes;
    }

    public void setDiablo2Quotes(List<Diablo2Quotes> diablo2Quotes) {
        this.diablo2Quotes = diablo2Quotes;
    }

    @Override
    public String toString() {
        return "Diablo2Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", diablo2Quotes=" + diablo2Quotes +
                '}';
    }
}
