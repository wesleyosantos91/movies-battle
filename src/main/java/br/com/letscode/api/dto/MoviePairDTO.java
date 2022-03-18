package br.com.letscode.api.dto;

import br.com.letscode.api.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.LongStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MoviePairDTO {

    private Movie firstMovie;
    private Movie secondMovie;

    public MoviePairDTO getInverse() {
        return new MoviePairDTO(this.secondMovie, this.firstMovie);
    }

}
