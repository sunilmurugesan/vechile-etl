package com.abg.etl.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class VehicleEtlControllerTest {

    @InjectMocks
    private VehicleEtlController controller;


    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
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
        VehicleData data = new VehicleData();
        data.setCategory("CAR");
        data.setMake("BMW");
        data.setModel("5 Series");
        return data;
    }

    public static String asJson(final VehicleData obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

