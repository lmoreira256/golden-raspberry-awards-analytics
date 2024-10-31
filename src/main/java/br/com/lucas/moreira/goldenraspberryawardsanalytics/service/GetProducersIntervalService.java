package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.view.ProducerView;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetIntervalsDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.IntervalDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.GetIntervalsDTOFactory;
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

    public GetIntervalsDTO execute() {
        List<ProducerView> producerViews = producerRepository.getProducersWithInterval();
        List<IntervalDTO> intervalDTOS = ProducerDTOFactory.build(producerViews);

        return GetIntervalsDTOFactory.build(
                getMinProducersInterval(intervalDTOS), getMaxProducersInterval(intervalDTOS));
    }

    private List<IntervalDTO> getMinProducersInterval(List<IntervalDTO> intervalDTOS) {
        Integer minInterval = intervalDTOS.get(0).getInterval();

        return intervalDTOS.stream().filter(producer -> Objects.equals(producer.getInterval(), minInterval)).toList();
    }

    private List<IntervalDTO> getMaxProducersInterval(List<IntervalDTO> intervalDTOS) {
        Integer maxInterval = intervalDTOS.get(intervalDTOS.size() - 1).getInterval();

        return intervalDTOS.stream().filter(producer -> Objects.equals(producer.getInterval(), maxInterval)).toList();
    }

}
