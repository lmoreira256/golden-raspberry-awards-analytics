package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.http;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetIntervalsDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.service.GetProducersIntervalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards")
public class AwardsResource {

    private final GetProducersIntervalService getProducersIntervalService;

    AwardsResource(GetProducersIntervalService getProducersIntervalService) {
        this.getProducersIntervalService = getProducersIntervalService;
    }

    @CrossOrigin
    @GetMapping(value = "/intervals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetIntervalsDTO> getProducersInterval() {

        return new ResponseEntity<>(getProducersIntervalService.execute(), HttpStatus.OK);
    }

}
