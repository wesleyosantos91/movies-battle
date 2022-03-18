package br.com.letscode.api.client;

import br.com.letscode.api.client.dto.MovieDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "movieDetail", url = "${client.omdbapi.url}")
public interface GetMovieByImdbId {

    @GetMapping
    MovieDetail detail(@RequestParam("i") String imdbId);
}
