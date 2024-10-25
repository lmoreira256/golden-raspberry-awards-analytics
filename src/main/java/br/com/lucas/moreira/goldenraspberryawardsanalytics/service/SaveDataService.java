package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.ProducerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDataService {

    private final SaveProducerService saveProducerService;
    private final SaveNominatedMovieService saveNominatedMovieService;

    SaveDataService(SaveProducerService saveProducerService,
                    SaveNominatedMovieService saveNominatedMovieService) {
        this.saveProducerService = saveProducerService;
        this.saveNominatedMovieService = saveNominatedMovieService;
    }

    public void execute(List<NominatedMovieDTO> nominatedMovieDTOS) {
        nominatedMovieDTOS.forEach(nominatedMovieDTO -> {
            List<Producer> producer = ProducerFactory.build(nominatedMovieDTO.getProducers());

            saveNominatedMovieService.execute(nominatedMovieDTO, saveProducerService.execute(producer));
        });
    }

}
