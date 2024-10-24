package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.ProducerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDataService {

    private final SaveProducerService saveProducerService;
    private final SaveMovieService saveMovieService;

    SaveDataService(SaveProducerService saveProducerService,
                    SaveMovieService saveMovieService) {
        this.saveProducerService = saveProducerService;
        this.saveMovieService = saveMovieService;
    }

    public void execute(List<NominatedMovieDTO> nominatedMovieDTOS) {
        nominatedMovieDTOS.forEach(nominatedMovieDTO -> {
            List<Producer> producer = ProducerFactory.build(nominatedMovieDTO.getProducers());

            saveMovieService.execute(nominatedMovieDTO, saveProducerService.execute(producer));
        });
    }

}
