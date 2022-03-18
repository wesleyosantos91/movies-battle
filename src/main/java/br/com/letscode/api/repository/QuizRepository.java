package br.com.letscode.api.repository;

import br.com.letscode.api.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByGameId(Long id);

    Quiz findByIdAndGameId(Long idQuiz, Long GameId);
}
