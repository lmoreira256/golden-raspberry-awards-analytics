package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.MovieDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;

@Service
public class FillDatabaseService {

    private final GetFileService getFileService;
    private final SaveDataService saveDataService;

    FillDatabaseService(GetFileService getFileService,
                        SaveDataService saveDataService) {
        this.getFileService = getFileService;
        this.saveDataService = saveDataService;
    }

    @Value("${path.file.csv}")
    private String filePath;

    public void execute() throws FileNotFoundException {
        Reader file = getFileService.execute(filePath);
        List<MovieDTO> movieDTOS = convertCsvToDto(file);

        saveDataService.execute(movieDTOS);
    }

    private List<MovieDTO> convertCsvToDto(Reader file) {
        return new CsvToBeanBuilder(file)
                .withSeparator(';')
                .withType(MovieDTO.class)
                .build().parse();
    }

}
