package br.com.letscode.api.service;

import br.com.letscode.api.dto.RankingDTO;
import br.com.letscode.api.dto.ResponseGameQuizDTO;
import br.com.letscode.api.entity.Game;
import br.com.letscode.api.entity.Quiz;
import br.com.letscode.api.entity.User;
import br.com.letscode.api.repository.GameRepository;
import br.com.letscode.api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository repository;
    private final UserRepository userRepository;
    private final QuizService quizService;

    public GameService(GameRepository repository, UserRepository userRepository, QuizService quizService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.quizService = quizService;
    }

    @Transactional
    public ResponseGameQuizDTO init() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(email);
        Game game = new Game();
        game.setUser(user);
        Game save = repository.save(game);
        Quiz quiz = quizService.save(save.getId());

        return new ResponseGameQuizDTO(save.getId(), quiz.getId());
    }

    public Game getById(Long id) {
        return repository.getById(id);
    }


    public void finish(Long id) {
        Game game = repository.getById(id);
        game.setOpenGame(false);
        game.setScore(game.getCorrect());
        repository.save(game);
    }

    public List<RankingDTO> getRanking() {
        return repository.findByOpenGameFalseOrderByScoreDesc()
                .stream()
                .map(g -> new RankingDTO(g.getUser().getFirstName(), g.getId(), g.getScore())).collect(Collectors.toList());
    }
}
