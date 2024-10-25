package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.NominatedMovieFactory;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.NominatedMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveNominatedMovieService {

    private final NominatedMovieRepository nominatedMovieRepository;

    public SaveNominatedMovieService(NominatedMovieRepository nominatedMovieRepository) {
        this.nominatedMovieRepository = nominatedMovieRepository;
    }

    public void execute(NominatedMovieDTO nominatedMovieDTO, List<Producer> producers) {

        nominatedMovieRepository.save(NominatedMovieFactory.build(nominatedMovieDTO, producers));
    }

}
