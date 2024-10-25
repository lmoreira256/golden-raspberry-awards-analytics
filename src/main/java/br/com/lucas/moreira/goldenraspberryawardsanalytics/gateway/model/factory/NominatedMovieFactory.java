package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.NominatedMovie;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;

import java.util.List;

public class NominatedMovieFactory {

    public static NominatedMovie build(NominatedMovieDTO nominatedMovieDTO, List<Producer> producers) {
        return NominatedMovie.builder()
                .producers(producers)
                .yearIndication(nominatedMovieDTO.getYear())
                .title(nominatedMovieDTO.getTitle())
                .winner(nominatedMovieDTO.isWinner())
                .build();
    }

}
