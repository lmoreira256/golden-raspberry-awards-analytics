package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.factory;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetIntervalsDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.IntervalDTO;

import java.util.List;

public class GetIntervalsDTOFactory {

    public static GetIntervalsDTO build(List<IntervalDTO> min, List<IntervalDTO> max) {
        return GetIntervalsDTO.builder()
                .mim(min)
                .max(max)
                .build();
    }

}
