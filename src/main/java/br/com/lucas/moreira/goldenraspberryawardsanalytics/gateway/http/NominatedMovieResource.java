package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.http;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetNominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.service.GetProducersIntervalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/golden-raspberry-awards")
public class NominatedMovieResource {

    private GetProducersIntervalService getProducersIntervalService;

    NominatedMovieResource(GetProducersIntervalService getProducersIntervalService) {
        this.getProducersIntervalService = getProducersIntervalService;
    }

    @CrossOrigin
    @GetMapping(value = "/get-producers-interval", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetNominatedMovieDTO> getProducersInterval() {

        return new ResponseEntity<>(getProducersIntervalService.execute(), HttpStatus.OK);
    }

}
