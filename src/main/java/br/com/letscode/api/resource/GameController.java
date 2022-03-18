package br.com.letscode.api.resource;

import br.com.letscode.api.dto.RankingDTO;
import br.com.letscode.api.dto.ResponseGameQuizDTO;
import br.com.letscode.api.entity.Game;
import br.com.letscode.api.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseGameQuizDTO start() throws RuntimeException {
        return service.init();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(Long id) throws RuntimeException {
        service.finish(id);
    }

    @GetMapping("/rankings")
    public List<RankingDTO> getRanquinking() {
        return service.getRanking();
    }
}
