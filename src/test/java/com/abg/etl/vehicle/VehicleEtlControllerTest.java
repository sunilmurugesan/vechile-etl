package com.abg.etl.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleEtlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerTemplate producerTemplate;

    @Before
    public void setUp() {
    }

    @Test
    public void canSuccessfullyLoadVehicleData() throws Exception {
        String url = "/vehicle/load";
        mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJson(vehicleData())))
                .andExpect(status().isOk());
    }

    public VehicleData vehicleData() {
        return VehicleData.builder().category("CAR").make("BMW").model("5 Series").year(2019).build();
    }


    public static String asJson(final VehicleData obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

