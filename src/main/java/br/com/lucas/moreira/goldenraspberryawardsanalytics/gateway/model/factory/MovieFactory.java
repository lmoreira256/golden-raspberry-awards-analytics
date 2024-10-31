package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Movie;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.MovieDTO;

import java.util.List;

public class MovieFactory {

    public static Movie build(MovieDTO movieDTO, List<Producer> producers) {
        return Movie.builder()
                .producers(producers)
                .yearIndication(movieDTO.getYear())
                .title(movieDTO.getTitle())
                .winner(movieDTO.isWinner())
                .build();
    }

}
