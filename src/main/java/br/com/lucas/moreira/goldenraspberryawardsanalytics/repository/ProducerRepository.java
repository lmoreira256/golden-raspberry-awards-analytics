package br.com.lucas.moreira.goldenraspberryawardsanalytics.repository;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    Optional<Producer> getProducerByName(String name);

}
