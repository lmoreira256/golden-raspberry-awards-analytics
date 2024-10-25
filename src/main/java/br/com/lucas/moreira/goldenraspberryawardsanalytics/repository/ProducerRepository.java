package br.com.lucas.moreira.goldenraspberryawardsanalytics.repository;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.view.ProducerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    Optional<Producer> getProducerByName(String name);

    @Query(value = "SELECT p.name AS producer, MIN(nm.year_indication) AS previousWin, MAX(nm.year_indication) AS followingWin,  MAX(nm.year_indication) - MIN(nm.year_indication) AS intervalValue " +
            "FROM producer p " +
            "INNER JOIN producer_nominated_movie_mapping AS pnm ON pnm.producer_id = p.id " +
            "INNER JOIN nominated_movie AS nm ON nm.id = pnm.nominated_movie_id AND nm.winner = true " +
            "GROUP BY p.name " +
            "HAVING COUNT(p.name) > 1 " +
            "ORDER BY intervalValue ASC ", nativeQuery = true)
    List<ProducerView> getProducersWithInterval();

}
