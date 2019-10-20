package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ParkingLot findByName(String parkingLotName) {
        return parkingLotRepository.findByname(parkingLotName);
    }

    public Boolean saveParkingLot(ParkingLot parkingLot) {
        ParkingLot findParkingLot = parkingLotRepository.findByname(parkingLot.getName());
        if ( findParkingLot == null) {
            parkingLotRepository.save(parkingLot);
            return true;
        } return false;
    }

    public Boolean deleteParkingLot(String parkingLotName) {
        ParkingLot parkingLotToDelete = parkingLotRepository.findByname(parkingLotName);
        if ( parkingLotToDelete != null) {
            parkingLotRepository.delete(parkingLotToDelete);
            return true;
        } return false;
    }
}