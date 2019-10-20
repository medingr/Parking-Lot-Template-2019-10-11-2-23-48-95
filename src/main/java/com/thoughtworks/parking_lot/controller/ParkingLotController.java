package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/parkingLot")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ParkingLot> getParkingLotByParkingName
            (@RequestParam(required = false, defaultValue = "") String name) {
        ParkingLot fetchedParkingLot = parkingLotService.findByName(name);
        if (!isNull(fetchedParkingLot)) {
            return new ResponseEntity<>(fetchedParkingLot, HttpStatus.OK);
        }
        else return new ResponseEntity<>(fetchedParkingLot, HttpStatus.BAD_REQUEST);
    }

    @PostMapping (produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ParkingLot> createParkingLot
            (@RequestBody(required = true) ParkingLot parkingLot)
    {
       if( parkingLotService.saveParkingLot(parkingLot)) {
           return new ResponseEntity<>(parkingLot , HttpStatus.CREATED);
       }
        return new ResponseEntity<>(parkingLot , HttpStatus.CREATED);
    }
}
