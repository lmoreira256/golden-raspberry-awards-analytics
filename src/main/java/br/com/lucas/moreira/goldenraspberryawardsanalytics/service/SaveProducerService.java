package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.NominatedMovieFactory;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.NominatedMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveProducerService {

    private final NominatedMovieRepository nominatedMovieRepository;

    public SaveProducerService(NominatedMovieRepository nominatedMovieRepository) {
        this.nominatedMovieRepository = nominatedMovieRepository;
    }

    public void execute(List<NominatedMovieDTO> nominatedMovieDTO) {
        nominatedMovieRepository.saveAll(nominatedMovieDTO.stream().map(NominatedMovieFactory::build).toList());
    }

}
