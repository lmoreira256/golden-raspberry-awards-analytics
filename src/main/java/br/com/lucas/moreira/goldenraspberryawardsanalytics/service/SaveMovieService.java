package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.NominatedMovie;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.NominatedMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveMovieService {

    private final NominatedMovieRepository nominatedMovieRepository;

    public SaveMovieService(NominatedMovieRepository nominatedMovieRepository) {
        this.nominatedMovieRepository = nominatedMovieRepository;
    }

    public void execute(NominatedMovieDTO nominatedMovieDTO, List<Producer> producers) {
        NominatedMovie nominatedMovie = NominatedMovie.builder()
                .producers(producers)
                .year(nominatedMovieDTO.getYear())
                .title(nominatedMovieDTO.getTitle())
                .winner(nominatedMovieDTO.isWinner())
                .build();

        nominatedMovieRepository.save(nominatedMovie);
    }

}
