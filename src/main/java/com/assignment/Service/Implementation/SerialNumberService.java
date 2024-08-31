package com.assignment.Service.Implementation;

import com.assignment.Entity.Item;
import com.assignment.Entity.Packet;
import com.assignment.Entity.SerialNumber;
import com.assignment.Repository.SerialNumberRepository;
import com.assignment.Service.ISerialNumberService;
import com.assignment.Utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing serial number associated with items.
 * Implements the ISerialNumberService interface.
 */
@Service
@RequiredArgsConstructor
public class SerialNumberService implements ISerialNumberService {

    private final SerialNumberRepository serialNumberRepository;

    /**
     * Generates and saves unique serial numbers for the given item and packet.
     *
     * @param item   The item for which serial numbers are generated.
     * @param packet The packet to which the serial numbers belong.
     * @throws IllegalArgumentException If either item or packet is null.
     */
    @Override
    public void generateSerialNumbers(Item item, Packet packet) {
        if (item == null || packet == null) {
            throw new IllegalArgumentException("Item and packet cannot be null");
        }

        // Generate serial numbers based on the item's pack quantity
        for (int i = 0; i < item.getPackQuantity(); i++) {
            SerialNumber serialNumber = new SerialNumber();

            // Generate a unique serial number
            serialNumber.setSerialNumber(Utils.uniqueNumberGenerator("SN", i));
            // Associate the serial number with the item
            serialNumber.setItem(item);
            // Associate the serial number with the packet
            serialNumber.setPacket(packet);

            serialNumberRepository.save(serialNumber);
        }
    }
}
