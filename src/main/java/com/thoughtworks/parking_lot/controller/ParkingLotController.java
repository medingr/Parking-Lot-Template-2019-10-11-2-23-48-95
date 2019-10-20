package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping(value = "/{name}" ,produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ParkingLot> getParkingLotByParkingName
            (@PathVariable String name) {
        ParkingLot fetchedParkingLot = parkingLotService.findByName(name);
        if (!isNull(fetchedParkingLot)) {
            return new ResponseEntity<>(fetchedParkingLot, HttpStatus.OK);
        }
        else return new ResponseEntity<>(fetchedParkingLot, HttpStatus.BAD_REQUEST);
    }

    @PostMapping (produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ParkingLot> createParkingLot
            (@RequestBody ParkingLot parkingLot)
    {
       if( parkingLotService.saveParkingLot(parkingLot)) {
           return new ResponseEntity<>(parkingLot , HttpStatus.CREATED);
       }
        return new ResponseEntity<>(parkingLot , HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{name}" , produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ParkingLot> deleteParkingLot (@PathVariable String name)
    {
        if( parkingLotService.deleteParkingLot(name)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE )
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<ParkingLot> getAllparkingLots(
    @RequestParam(required = false , defaultValue = "0")  Integer page ) {
        return parkingLotService.findAll(PageRequest.of(page,15, Sort.by("name").ascending()));
    }

    @PatchMapping(path = "/{parkingLotName}", produces = {"application/json"})
    public ResponseEntity<ParkingLot> updateParkingLotCapacity(@PathVariable String parkingLotName,
                                                               @RequestBody ParkingLot parkingLotToUpdate) {
        ParkingLot parkingLotForUpdate = parkingLotService.updateParkingLotCapacity(parkingLotName, parkingLotToUpdate);
        if (!isNull(parkingLotForUpdate)) {
            return new ResponseEntity<>(parkingLotForUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
