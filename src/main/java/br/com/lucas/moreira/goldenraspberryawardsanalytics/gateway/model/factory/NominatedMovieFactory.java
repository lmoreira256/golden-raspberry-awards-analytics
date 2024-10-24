package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.NominatedMovie;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;

public class NominatedMovieFactory {

    public static NominatedMovie build(NominatedMovieDTO nominatedMovieDTO) {
        return NominatedMovie.builder()
                .year(nominatedMovieDTO.getYear())
                .title(nominatedMovieDTO.getTitle())
                .producers(nominatedMovieDTO.getProducers())
                .winner(nominatedMovieDTO.isWinner())
                .build();
    }

}
