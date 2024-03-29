package org.StayAwhileAndListen.repository;

import org.StayAwhileAndListen.model.Diablo2Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Diablo2CharacterRepository extends JpaRepository<Diablo2Character, Long>, CrudRepository<Diablo2Character, Long> {

    Optional<Diablo2Character> findFirstByName(String name);

}
