package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntervalDTO {

    private String producer;

    private Integer interval;

    private Integer previousWin;

    private Integer followingWin;

}
