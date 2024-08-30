package com.assignment.Service;

import com.assignment.Entity.Item;
import com.assignment.Entity.Packet;
import com.assignment.Repository.PacketRepository;
import com.assignment.Utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacketService {

    private final PacketRepository packetRepository;
    private final SerialNumberService serialNumberService;

    /**
     * Generates packets for the given item based on its quantity and saves them to the repository.
     *
     * @param item The item for which packets are to be generated
     */
    public void generatePackets(Item item) {
       // Loop Quantity mentioned in
        for (int i = 0; i < item.getQuantity(); i++) {

            // Create a new packet InstanCe
            Packet packet = new Packet();

            // Set a unique packet number for the packet
            packet.setPacketNumber(Utils.uniqueNumberGenerator("PKT", i));
            // Associate the packet with the item
            packet.setItem(item);

            packetRepository.save(packet);

            // Generate and associate unique serial numbers for the item within this packet
            serialNumberService.generateSerialNumbers(item, packet);
        }
    }
}
