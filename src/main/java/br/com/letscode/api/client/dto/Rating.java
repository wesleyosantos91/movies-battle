package br.com.letscode.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating {

    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}
