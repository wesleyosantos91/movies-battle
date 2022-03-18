package br.com.letscode.api.resource;

import br.com.letscode.api.dto.ResponseGameQuizDTO;
import br.com.letscode.api.dto.ResponseQuizDTO;
import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.entity.Quiz;
import br.com.letscode.api.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quizs")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }


    @GetMapping("{id}/games/{idGame}")
    public ResponseQuizDTO getQuizByidAndGameId(@PathVariable Long id, @PathVariable Long idGame) {
        return service.getQuizByIdAndGameId(id, idGame);
    }

    @PostMapping("{id}/games/{idGame}")
    public ResponseGameQuizDTO getQuizByidAndGameId(@PathVariable Long id,
                                                    @PathVariable Long idGame,
                                                    @RequestBody String idMovie) {
        return service.responseQuiz(id, idGame, idMovie);
    }


}
