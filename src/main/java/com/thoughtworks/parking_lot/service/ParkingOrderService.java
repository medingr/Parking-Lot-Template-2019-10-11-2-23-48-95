package com.thoughtworks.parking_lot.service;


import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ParkingOrderService {

    @Autowired
    ParkingOrderRepository parkingOrderRepository;

    public ParkingOrder createParkingOrder(ParkingOrder parkingOrder, String parkingLotName) {
        ParkingOrder parkingOrderToCreate = new ParkingOrder();
        String localDate = LocalDate.now().toString();
        parkingOrderToCreate.setPlateNumber(parkingOrder.getPlateNumber());
        parkingOrderToCreate.setNameOfParkingLot(parkingLotName);
        parkingOrderToCreate.setCreationTime(localDate);
       return parkingOrderRepository.save(parkingOrderToCreate);
    }

}
