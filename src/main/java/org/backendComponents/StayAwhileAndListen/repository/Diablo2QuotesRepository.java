package org.backendComponents.StayAwhileAndListen.repository;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Diablo2QuotesRepository extends JpaRepository<Diablo2Quote, Long>, CrudRepository<Diablo2Quote, Long> {

    Optional<Diablo2Quote> findFirstByName(String name);

}
