package br.com.letscode.api.repository;


import br.com.letscode.api.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByOpenGameFalseOrderByScoreDesc();
}
