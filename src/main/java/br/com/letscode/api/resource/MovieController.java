package br.com.letscode.api.resource;

import br.com.letscode.api.dto.MoviesInsertDTO;
import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> salveMovies(@RequestBody MoviesInsertDTO moviesInsertDTO) throws RuntimeException {
        return service.saveMovies(moviesInsertDTO.getNames());
    }

    @GetMapping
    public List<Movie> get() throws RuntimeException {
        return service.find();
    }
}
