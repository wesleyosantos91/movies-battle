package br.com.letscode.api.service;

import br.com.letscode.api.dto.MoviePairDTO;
import br.com.letscode.api.dto.ResponseGameQuizDTO;
import br.com.letscode.api.dto.ResponseQuizDTO;
import br.com.letscode.api.entity.Game;
import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.entity.Quiz;
import br.com.letscode.api.repository.GameRepository;
import br.com.letscode.api.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {

    private final QuizRepository repository;
    private final MovieService movieService;
    private final GameRepository gameRepository;

    public QuizService(QuizRepository repository, MovieService movieService, GameRepository gameRepository) {
        this.repository = repository;
        this.movieService = movieService;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Quiz save(Long idGame) {
        Set<MoviePairDTO> moviePairDTOS = repository.findByGameId(idGame).stream().map(q -> new MoviePairDTO(q.getFirtMovie(), q.getSecondMovie())).collect(Collectors.toSet());
        MoviePairDTO moviePairDTO = movieService.selectMovie(moviePairDTOS);
        Quiz quiz = new Quiz();
        quiz.setFirtMovie(moviePairDTO.getFirstMovie());
        quiz.setSecondMovie(moviePairDTO.getSecondMovie());
        Game game = new Game();
        game.setId(idGame);
        quiz.setGame(game);
        return repository.save(quiz);
    }

    @Transactional(readOnly = true)
    public ResponseQuizDTO getQuizByIdAndGameId(Long idQuiz, Long idGame) {
        Quiz quiz = repository.findByIdAndGameId(idQuiz, idGame);

        return new ResponseQuizDTO(quiz.getId(),
                quiz.getGame().getId(),
                quiz.getFirtMovie().getTitle(),
                quiz.getFirtMovie().getImdbId(),
                quiz.getSecondMovie().getTitle(),
                quiz.getSecondMovie().getImdbId());
    }

    @Transactional
    public ResponseGameQuizDTO responseQuiz(Long idQuiz, Long idGame, String idMovie) {
        Quiz quiz = repository.findByIdAndGameId(idQuiz, idGame);

        Movie result = Stream.of(quiz.getFirtMovie(), quiz.getSecondMovie()).max(Comparator.comparing(Movie::getPontuation)).orElseThrow();
        Quiz newQuiz = save(idGame);

        quiz.setOpenQuiz(false);

        if (result.getImdbId().equalsIgnoreCase(idMovie)) {
            Game game = gameRepository.getById(idGame);
            game.setCorrect(game.getCorrect() + 1);
            gameRepository.save(game);
            return new ResponseGameQuizDTO(idGame, idQuiz, "correct", newQuiz.getId());

        }

        Game game = gameRepository.getById(idGame);
        game.setWrong(game.getWrong() + 1);
        gameRepository.save(game);

        if (game.getWrong() >= 3) {
            game.setOpenGame(false);
            game.setScore(game.getCorrect());
            gameRepository.save(game);
            return new ResponseGameQuizDTO(idGame, idQuiz, "Finish Game", null);
        }
        return new ResponseGameQuizDTO(idGame, idQuiz, "Wrong", newQuiz.getId());
    }


}
