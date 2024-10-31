package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.http;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetIntervalsDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.IntervalDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.MovieRepository;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.ProducerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testData() {
        Long TOTAL_PRODUCERS = 359L;
        Long TOTAL_MOVIES = 206L;

        assertEquals(TOTAL_PRODUCERS, producerRepository.count());
        assertEquals(TOTAL_MOVIES, movieRepository.count());
    }

    @Test
    public void testResource() throws Exception {
        MvcResult result = mvc.perform(get("/awards/intervals")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        GetIntervalsDTO nominatedMovieDTO = objectMapper.readValue(content, GetIntervalsDTO.class);

        assertNotNull(nominatedMovieDTO);

        List<IntervalDTO> intervalDTOMin = nominatedMovieDTO.getMim();
        List<IntervalDTO> intervalDTOMax = nominatedMovieDTO.getMax();

        testList(intervalDTOMin);
        testList(intervalDTOMax);

        intervalDTOMin.forEach(this::testMinValues);
        intervalDTOMax.forEach(this::testMaxValues);
    }

    private void testList(List<IntervalDTO> intervalDTOS) {
        assertNotNull(intervalDTOS);

        assertFalse(intervalDTOS.isEmpty());

        assertEquals(1, intervalDTOS.size());
    }

    private void testMinValues(IntervalDTO intervalDTO) {
        assertEquals("Joel Silver", intervalDTO.getProducer());
        assertEquals(1, intervalDTO.getInterval());
        assertEquals(1990, intervalDTO.getPreviousWin());
        assertEquals(1991, intervalDTO.getFollowingWin());
    }

    private void testMaxValues(IntervalDTO intervalDTO) {
        assertEquals("Matthew Vaughn", intervalDTO.getProducer());
        assertEquals(13, intervalDTO.getInterval());
        assertEquals(2002, intervalDTO.getPreviousWin());
        assertEquals(2015, intervalDTO.getFollowingWin());
    }

}
