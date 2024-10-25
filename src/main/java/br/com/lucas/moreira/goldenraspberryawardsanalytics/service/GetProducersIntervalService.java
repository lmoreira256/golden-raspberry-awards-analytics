package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.view.ProducerView;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetNominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.ProducerDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.ProducerDTOFactory;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GetProducersIntervalService {

    private final ProducerRepository producerRepository;

    GetProducersIntervalService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public GetNominatedMovieDTO execute() {
        List<ProducerView> producerViews = producerRepository.getProducersWithInterval();
        List<ProducerDTO> producerDTOS = ProducerDTOFactory.build(producerViews);

        return GetNominatedMovieDTO.builder()
                .mim(getMinProducersInterval(producerDTOS))
                .max(getMaxProducersInterval(producerDTOS))
                .build();
    }

    private List<ProducerDTO> getMinProducersInterval(List<ProducerDTO> producerDTOS) {
        Integer minInterval = producerDTOS.get(0).getInterval();

        return producerDTOS.stream().filter(x -> Objects.equals(x.getInterval(), minInterval)).toList();
    }

    private List<ProducerDTO> getMaxProducersInterval(List<ProducerDTO> producerDTOS) {
        Integer maxInterval = producerDTOS.get(producerDTOS.size() - 1).getInterval();

        return producerDTOS.stream().filter(x -> Objects.equals(x.getInterval(), maxInterval)).toList();
    }

}
