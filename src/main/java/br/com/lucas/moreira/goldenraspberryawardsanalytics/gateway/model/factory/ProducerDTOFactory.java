package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.view.ProducerView;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.ProducerDTO;

import java.util.List;

public class ProducerDTOFactory {

    public static List<ProducerDTO> build(List<ProducerView> producerViews) {
        return producerViews.stream().map(ProducerDTOFactory::build).toList();
    }

    public static ProducerDTO build(ProducerView producerView) {
        return ProducerDTO.builder()
                .producer(producerView.getProducer())
                .interval(producerView.getIntervalValue())
                .previousWin(producerView.getPreviousWin())
                .followingWin(producerView.getFollowingWin())
                .build();
    }

}
