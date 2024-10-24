package br.com.lucas.moreira.goldenraspberryawardsanalytics.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@Service
public class GetFileService {

    public Reader execute(String filePath) throws FileNotFoundException {
        return new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filePath));
    }

}
