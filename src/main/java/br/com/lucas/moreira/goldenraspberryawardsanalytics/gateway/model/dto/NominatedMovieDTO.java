package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NominatedMovieDTO {

    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}
