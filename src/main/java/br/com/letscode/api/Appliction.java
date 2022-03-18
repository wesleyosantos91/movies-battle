package br.com.letscode.api;

import br.com.letscode.api.client.GetMovieByImdbId;
import br.com.letscode.api.client.GetMovieByMovieTitle;
import br.com.letscode.api.client.dto.MovieDetail;
import br.com.letscode.api.client.dto.ResultSearch;
import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.repository.MovieRepository;
import br.com.letscode.api.utils.DecimalFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.valueOf;

@EnableFeignClients
@SpringBootApplication
public class Appliction implements CommandLineRunner {

    @Value("${client.omdbapi.movies}")
    private List<String> namesMovies;

    private final MovieRepository repository;
    private final GetMovieByMovieTitle getMovieByMovieTitle;
    private final GetMovieByImdbId getMovieByImdbId;

    public Appliction(MovieRepository repository, GetMovieByMovieTitle getMovieByMovieTitle, GetMovieByImdbId getMovieByImdbId) {
        this.repository = repository;
        this.getMovieByMovieTitle = getMovieByMovieTitle;
        this.getMovieByImdbId = getMovieByImdbId;
    }

    public static void main(String[] args) {
        SpringApplication.run(Appliction.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        namesMovies.forEach(name -> {
            ResultSearch resultSearch = getMovieByMovieTitle.search(name);
            resultSearch.getResult().forEach(movieMinimal -> {
                MovieDetail detail = getMovieByImdbId.detail(movieMinimal.getImdbID());
                    repository.save(Movie.builder()
                            .title(movieMinimal.getTitle())
                            .year(movieMinimal.getYear())
                            .imdbId(movieMinimal.getImdbID())
                            .votes(DecimalFormatUtils.format(detail.getImdbVotes()).doubleValue())
                            .rating(valueOf(detail.getImdbRating()))
                            .build());
            });
        });

    }
}
