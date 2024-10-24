package br.com.lucas.moreira.goldenraspberryawardsanalytics.repository;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.NominatedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominatedMovieRepository extends JpaRepository<NominatedMovie, Integer> {
}
