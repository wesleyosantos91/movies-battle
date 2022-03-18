package br.com.letscode.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultSearch {

    @JsonProperty("Search")
    private List<MovieMinimal> result;
    @JsonProperty("totalResults")
    private Integer total;
    @JsonProperty("Response")
    private Boolean response;
}
