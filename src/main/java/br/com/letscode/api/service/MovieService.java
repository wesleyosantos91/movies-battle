package br.com.letscode.api.service;

import br.com.letscode.api.client.GetMovieByImdbId;
import br.com.letscode.api.client.GetMovieByMovieTitle;
import br.com.letscode.api.client.dto.MovieDetail;
import br.com.letscode.api.client.dto.ResultSearch;
import br.com.letscode.api.dto.MoviePairDTO;
import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.repository.MovieRepository;
import br.com.letscode.api.utils.DecimalFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Double.valueOf;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final GetMovieByImdbId getMovieByImdbId;
    private final GetMovieByMovieTitle getMovieByMovieTitle;

    public MovieService(MovieRepository repository, GetMovieByImdbId getMovieByImdbId, GetMovieByMovieTitle getMovieByMovieTitle) {
        this.repository = repository;
        this.getMovieByImdbId = getMovieByImdbId;
        this.getMovieByMovieTitle = getMovieByMovieTitle;
    }

    @Transactional(readOnly = true)
    public List<Movie> find() throws RuntimeException {
        return repository.findAll();
    }

    @Transactional
    public List<Movie> saveMovies(List<String> names) throws RuntimeException {

        List<Movie> movies = new ArrayList<>();
        List<ResultSearch> resultList = names.stream()
                .map(this.getMovieByMovieTitle::search)
                .collect(Collectors.toList());
        ResultSearch resultSearch = reduce(resultList);

        for(int i = 0; i < resultSearch.getResult().size(); i++) {

            resultSearch.getResult().forEach(movieMinimal -> {
                MovieDetail detail = getMovieByImdbId.detail(movieMinimal.getImdbID());
                Movie movie = repository.save(Movie.builder()
                        .title(movieMinimal.getTitle())
                        .year(movieMinimal.getYear())
                        .imdbId(movieMinimal.getImdbID())
                        .votes(DecimalFormatUtils.format(detail.getImdbVotes()).doubleValue())
                        .rating(valueOf(detail.getImdbRating()))
                        .build());
                movies.add(movie);
            });
        }
        return movies;
    }

    public MoviePairDTO selectMovie(Set<MoviePairDTO> moviePairDTOS) throws RuntimeException {

        MoviePairDTO moviePairDTO = new MoviePairDTO(randomMovie(), randomMovie());

        while (moviePairDTOS.contains(moviePairDTO) || moviePairDTOS.contains(moviePairDTO.getInverse())) {
            moviePairDTO = new MoviePairDTO(randomMovie(), randomMovie());
        }

        return moviePairDTO;
    }

    private ResultSearch reduce(List<ResultSearch> resultList) {
        return ResultSearch.builder()
                .result(resultList.stream().map(ResultSearch::getResult).flatMap(Collection::stream).collect(
                        Collectors.toList()))
                .response(resultList.stream().map(ResultSearch::getResponse).reduce(true, Boolean::logicalAnd))
                .total(resultList.stream().map(ResultSearch::getTotal).reduce(0, Integer::sum))
                .build();
    }

    private Movie randomMovie() throws RuntimeException  {
        var movies = find();
        var random = new Random();
        return movies.get(random.nextInt(movies.size()));
    }
}
