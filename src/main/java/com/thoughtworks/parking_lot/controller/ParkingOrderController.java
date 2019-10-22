package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/parkingLot/{parkingLotName}/parkingOrder")
public class ParkingOrderController {

    @Autowired
    ParkingOrderService parkingOrderService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ParkingOrder> createParkingOrder(@PathVariable String parkingLotName, @RequestBody ParkingOrder parkingOrder)
    {
        ParkingOrder newParkingOrder = parkingOrderService.createParkingOrder(parkingOrder , parkingLotName);
        return new ResponseEntity<>(newParkingOrder , HttpStatus.CREATED);
    }
}
