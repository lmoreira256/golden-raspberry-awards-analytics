package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetNominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.ProducerDTO;

import java.util.List;

public class GetNominatedMovieDTOFactory {

    public static GetNominatedMovieDTO build(List<ProducerDTO> min, List<ProducerDTO> max) {
        return GetNominatedMovieDTO.builder()
                .mim(min)
                .max(max)
                .build();
    }

}
