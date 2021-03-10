package org.backendComponents.StayAwhileAndListen.repository;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Diablo2QuotesRepository extends JpaRepository<Diablo2Quotes, Long>, CrudRepository<Diablo2Quotes, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM diablo2quotes")
    List<Diablo2Quotes> getAllQuotes();

    Diablo2Quotes getFirstById(Long id);

    Optional<Diablo2Quotes> findFirstByName(String name);
}
