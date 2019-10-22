package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingOrderController.class)
@ActiveProfiles(profiles = "test")
public class ParkingOrderControllerTest {

    @MockBean
    private ParkingOrderService parkingOrderService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_parking_order_when_available_space_in_parking_lot () throws Exception {//given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLotOne");

        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setPlateNumber("PLATE001");
        parkingOrder.setNameOfParkingLot("parkingLotOne");

        when(parkingOrderService.createParkingOrder(any(), eq(parkingLot.getName()))).thenReturn(parkingOrder);
        //when
        ResultActions result = mvc.perform(post("/parkingLot/parkingLotOne/parkingOrder")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingOrder)));

        //then
        result.andExpect(status().isCreated())
                .andReturn();

    }
}
