package br.com.letscode.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseGameQuizDTO {

    private Long idGame;
    private Long idQuiz;
    private String response;
    private Long idNewQuiz;

    public ResponseGameQuizDTO(Long idGame, Long idQuiz) {
        this.idGame = idGame;
        this.idQuiz = idQuiz;
    }
}
