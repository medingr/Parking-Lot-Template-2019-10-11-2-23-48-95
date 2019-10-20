package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@ActiveProfiles(profiles = "test")
public class ParkingLotControllerTest {

    @MockBean
    private ParkingLotService parkingLotService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shoud_return_parking_lot_when_find_parking_lot_by_name() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLotOne");

        when(parkingLotService.findByName("parkingLotOne")).thenReturn(parkingLot);
        //when
        ResultActions result = mvc.perform(get("/parkingLot?name=parkingLotOne")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(parkingLot)));
        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void should_create_parking_lot_when_create_parking_lot () throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLotOne");
        parkingLot.setCapacity(20);
        parkingLot.setLocation("South");

        when(parkingLotService.saveParkingLot(parkingLot)).thenReturn(true);
        //when
        ResultActions result = mvc.perform(post("/parkingLot")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingLot)));

        //then
        result.andExpect(status().isCreated())
                .andReturn();

    }
}
