package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.MovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory.MovieFactory;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveMovieService {

    private final MovieRepository movieRepository;

    public SaveMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void execute(MovieDTO nominatedMovieDTO, List<Producer> producers) {

        movieRepository.save(MovieFactory.build(nominatedMovieDTO, producers));
    }

}
