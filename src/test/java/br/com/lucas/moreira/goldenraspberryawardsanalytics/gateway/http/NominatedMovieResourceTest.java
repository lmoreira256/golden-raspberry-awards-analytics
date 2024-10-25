package br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.http;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.NominatedMovie;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.domain.Producer;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.gateway.model.dto.GetNominatedMovieDTO;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.NominatedMovieRepository;
import br.com.lucas.moreira.goldenraspberryawardsanalytics.repository.ProducerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NominatedMovieResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private NominatedMovieRepository nominatedMovieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        producerRepository.deleteAll();
        nominatedMovieRepository.deleteAll();

        List<Producer> producers = List.of(
                Producer.builder().name("Adam Sandler").build(),
                Producer.builder().name("Ray Stark").build(),
                Producer.builder().name("Rupert Hitzig").build(),
                Producer.builder().name("Gloria Katz").build()
        );

        producers = producerRepository.saveAll(producers);

        List<NominatedMovie> nominatedMovies = List.of(
                NominatedMovie.builder()
                        .yearIndication(2000)
                        .title("Expend4bles")
                        .producers(List.of(producers.get(0)))
                        .winner(true)
                        .build(),
                NominatedMovie.builder()
                        .yearIndication(2001)
                        .title("The Mummy")
                        .producers(List.of(producers.get(0), producers.get(2)))
                        .winner(true)
                        .build(),
                NominatedMovie.builder()
                        .yearIndication(2005)
                        .title("Left Behind")
                        .producers(List.of(producers.get(3), producers.get(2)))
                        .winner(true)
                        .build(),
                NominatedMovie.builder()
                        .yearIndication(2007)
                        .title("Battleship")
                        .producers(List.of(producers.get(3)))
                        .winner(false)
                        .build(),
                NominatedMovie.builder()
                        .yearIndication(2009)
                        .title("The Mummy")
                        .producers(List.of(producers.get(1)))
                        .winner(false)
                        .build(),
                NominatedMovie.builder()
                        .yearIndication(2024)
                        .title("The Emoji Movie")
                        .producers(List.of(producers.get(3)))
                        .winner(true)
                        .build()
        );

        nominatedMovieRepository.saveAll(nominatedMovies);
    }

    @Test
    public void testCallResource() throws Exception {
        MvcResult result = mvc.perform(get("/golden-raspberry-awards/get-producers-interval")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        GetNominatedMovieDTO nominatedMovieDTO = objectMapper.readValue(content, GetNominatedMovieDTO.class);

        assertNotNull(nominatedMovieDTO);
        assertEquals(1, nominatedMovieDTO.getMim().size());
        assertEquals(1, nominatedMovieDTO.getMax().size());

        assertEquals(1, nominatedMovieDTO.getMim().get(0).getInterval());
        assertEquals(19, nominatedMovieDTO.getMax().get(0).getInterval());

        assertEquals("Adam Sandler", nominatedMovieDTO.getMim().get(0).getProducer());
        assertEquals("Gloria Katz", nominatedMovieDTO.getMax().get(0).getProducer());

        assertTrue(nominatedMovieDTO.getMim().stream().filter(x -> x.getProducer().equals("Rupert Hitzig")).toList().isEmpty());
        assertTrue(nominatedMovieDTO.getMax().stream().filter(x -> x.getProducer().equals("Rupert Hitzig")).toList().isEmpty());
    }

}
