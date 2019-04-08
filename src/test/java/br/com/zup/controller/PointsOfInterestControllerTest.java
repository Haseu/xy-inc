package br.com.zup.controller;

import br.com.zup.Repository.PoiRepository;
import br.com.zup.ZupApplicationTests;
import br.com.zup.entity.Poi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PointsOfInterestControllerTest extends ZupApplicationTests {

    private final String CONTEXT = "/api/poi";

    @Autowired
    PoiRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        int total = (int) repository.count();

        this.mockMvc.perform(get(CONTEXT)).andExpect(status()
                .isOk())
                .andExpect(jsonPath("totalElements", Matchers.is(total)));
    }

    @Test
    public void create() throws Exception {

        String request = "{\n" +
                "  \"name\" : \"Sorveteria\",\n" +
                "  \"coordinateX\" : 25,\n" +
                "  \"coordinateY\" : 9\n" +
                "}";

        String jsonResponse = this.mockMvc.perform(post(CONTEXT).contentType(APPLICATION_JSON).content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Poi poi = mapper.readValue(jsonResponse, Poi.class);

        Optional<Poi> poiCreated = repository.findById(poi.getId());
        Poi pesistedPoi = poiCreated.get();

        Assertions.assertThat(pesistedPoi.getName()).isEqualTo("Sorveteria");
        Assertions.assertThat(pesistedPoi.getCoordinateX()).isEqualTo(25);
        Assertions.assertThat(pesistedPoi.getCoordinateY()).isEqualTo(9);

    }

    @Test
    public void nearbyPois() throws Exception {
        int total = (int) repository.findByDistance(
                PageRequest.of(0, 10),
                20,
                10,
                10.0)
                .getTotalElements();

        this.mockMvc.perform(
                get(CONTEXT + "/nearby?page=0&size=10&x=20&y=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements", Matchers.is(total)));
    }

    @Test
    public void validate() throws Exception {
        String request = "{\n" +
                "  \"name\" : \"Sorveteria\",\n" +
                "  \"coordinateX\" : -1,\n" +
                "  \"coordinateY\" : -12\n" +
                "}";

        String jsonResponse = this.mockMvc.perform(post(CONTEXT).contentType(APPLICATION_JSON).content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", Matchers.containsString("Validation Failed")))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}