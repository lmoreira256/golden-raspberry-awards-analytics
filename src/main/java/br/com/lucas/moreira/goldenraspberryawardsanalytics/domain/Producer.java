package br.com.lucas.moreira.goldenraspberryawardsanalytics.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producer")
public class Producer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "producer_nominated_movie_mapping", joinColumns = @JoinColumn(name = "producer_id"), inverseJoinColumns = @JoinColumn(name = "nominated_movie_id"))
    private List<NominatedMovie> nominatedMovies;

    @Column(name = "name")
    private String name;

}
