package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveProducerService {

    private final ProducerRepository producerRepository;

    public SaveProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<Producer> execute(List<Producer> producers) {
        return producers.stream().map(this::execute).toList();
    }

    public Producer execute(Producer producer) {
        Optional<Producer> producerOptional = producerRepository.getProducerByName(producer.getName());

        return producerOptional.orElseGet(() -> producerRepository.save(producer));
    }

}
