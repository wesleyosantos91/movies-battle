package br.com.letscode.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_quiz")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean openQuiz = true;

    @ManyToOne
    @JoinColumn(name = "firt_movie_id")
    private Movie firtMovie;

    @ManyToOne
    @JoinColumn(name = "second_movie_id")
    private Movie secondMovie;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
