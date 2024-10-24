package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.NominatedMovieDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;

@Service
public class FillDatabaseService {

    private final GetFileService getFileService;
    private final SaveMovieService saveMoviesService;
    private final SaveProducerService saveProducerService;
    private final SaveDataService saveDataService;

    FillDatabaseService(GetFileService getFileService,
                        SaveMovieService saveMoviesService,
                        SaveProducerService saveProducerService,
                        SaveDataService saveDataService) {
        this.getFileService = getFileService;
        this.saveMoviesService = saveMoviesService;
        this.saveProducerService = saveProducerService;
        this.saveDataService = saveDataService;
    }

    @Value("${path.file.csv}")
    private String filePath;

    public void execute() throws FileNotFoundException {
        Reader file = getFileService.execute(filePath);
        List<NominatedMovieDTO> nominatedMovieDTOS = convertCsvToDto(file);

        saveDataService.execute(nominatedMovieDTOS);
    }

    private List<NominatedMovieDTO> convertCsvToDto(Reader file) {
        return new CsvToBeanBuilder(file)
                .withSeparator(';')
                .withType(NominatedMovieDTO.class)
                .build().parse();
    }

}
