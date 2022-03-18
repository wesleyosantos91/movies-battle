package br.com.letscode.api.client;

import br.com.letscode.api.client.dto.ResultSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "movieMinimal", url = "${client.omdbapi.url}")
public interface GetMovieByMovieTitle {

    @GetMapping
    ResultSearch search(@RequestParam("s") String movieTitle);
}
