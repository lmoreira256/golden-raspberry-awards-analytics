package br.com.lucas.moreira.goldenraspberryawardsanalytics.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nominated_movie")
public class NominatedMovie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "year_indication")
    private Integer year;

    @Column(name = "title")
    private String title;

    @Column(name = "producers")
    private String producers;

    @Column(name = "winner")
    private boolean winner;

}
