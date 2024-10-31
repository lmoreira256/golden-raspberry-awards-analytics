package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.MovieDTO;
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

    public void execute(List<MovieDTO> movieDTOS) {
        movieDTOS.forEach(movieDTO -> {
            List<Producer> producer = ProducerFactory.build(movieDTO.getProducers());

            saveMovieService.execute(movieDTO, saveProducerService.execute(producer));
        });
    }

}
