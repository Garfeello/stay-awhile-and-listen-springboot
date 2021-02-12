package org.backendComponents.StayAwhileAndListen.repository;

import org.backendComponents.StayAwhileAndListen.model.Diablo2Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Diablo2CharacterRepository extends JpaRepository<Diablo2Character, Long>, CrudRepository<Diablo2Character, Long> {

    Optional<Diablo2Character> findDiablo2CharacterByName(String name);

}
