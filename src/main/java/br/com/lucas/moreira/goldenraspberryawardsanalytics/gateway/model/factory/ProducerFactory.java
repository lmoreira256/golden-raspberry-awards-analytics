package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;

import java.util.Arrays;
import java.util.List;

public class ProducerFactory {

    private static final String PRODUCER_SEPARATE_AND = " and ";
    private static final String PRODUCER_SEPARATE_COMMA = ", ";

    public static List<Producer> build(String producers) {
        return Arrays.stream(producers.replace(PRODUCER_SEPARATE_AND, PRODUCER_SEPARATE_COMMA)
                        .split(PRODUCER_SEPARATE_COMMA))
                .map(producer -> Producer.builder()
                        .name(producer)
                        .build()).toList();
    }

}
