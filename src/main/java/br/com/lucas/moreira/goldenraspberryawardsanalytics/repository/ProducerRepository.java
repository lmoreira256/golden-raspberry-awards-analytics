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

    @Query(value = "SELECT " +
            "PRODUCER, " +
            "YEAR_DIFF AS INTERVAL_VALUE, " +
            "YEAR1 AS PREVIOUS_WIN, " +
            "YEAR2 AS FOLLOWING_WIN " +
            "FROM ( " +
            " SELECT " +
            "   P.NAME AS PRODUCER, " +
            "   M.YEAR_INDICATION AS YEAR1, " +
            "   LEAD(M.YEAR_INDICATION) OVER (PARTITION BY P.ID ORDER BY M.YEAR_INDICATION) AS YEAR2, " +
            "   LEAD(M.YEAR_INDICATION) OVER (PARTITION BY P.ID ORDER BY M.YEAR_INDICATION) - M.YEAR_INDICATION AS YEAR_DIFF " +
            "FROM PRODUCER AS P " +
            "JOIN PRODUCER_MOVIE AS PM ON P.ID = PM.PRODUCER_ID " +
            "JOIN MOVIE AS M ON PM.MOVIE_ID = M.ID AND M.WINNER = TRUE " +
            ") AS PRODUCER_INTERVALS " +
            "WHERE PRODUCER_INTERVALS.YEAR_DIFF IS NOT NULL " +
            "ORDER BY INTERVAL_VALUE, PREVIOUS_WIN", nativeQuery = true)
    List<ProducerView> getProducersWithInterval();

}
