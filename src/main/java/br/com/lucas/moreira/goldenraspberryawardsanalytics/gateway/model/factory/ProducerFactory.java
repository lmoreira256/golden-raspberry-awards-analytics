package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;

import java.util.Arrays;
import java.util.List;

public class ProducerFactory {

    public static List<Producer> build(String producers) {
        return Arrays.stream(producers.replace(" and ", ", ").split(", "))
                .map(producer -> Producer.builder()
                        .name(producer)
                        .build()).toList();
    }

}
