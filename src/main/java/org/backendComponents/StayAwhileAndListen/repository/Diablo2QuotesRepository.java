package org.backendComponents.StayAwhileAndListen.repository;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Diablo2QuotesRepository extends JpaRepository<Diablo2Quotes, Long>, CrudRepository<Diablo2Quotes, Long> {

    @Query(value = "SELECT d FROM Diablo2Quotes d ")
    Optional<List<Diablo2Quotes>> getAllQuotes();

    @Query(nativeQuery = true, value = "SELECT * FROM diablo2quotes order by RAND() LIMIT 1")
    Optional<Diablo2Quotes> getRandomQuote();

    Optional<Diablo2Quotes> findFirstByName(String name);

    Optional<Diablo2Quotes> findFirstByDaily(LocalDate daily);
}
