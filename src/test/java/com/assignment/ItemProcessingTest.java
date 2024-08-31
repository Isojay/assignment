package com.assignment;

import com.assignment.DTO.ItemDTO;
import com.assignment.Entity.Item;
import com.assignment.Entity.Packet;
import com.assignment.Entity.PackingType;
import com.assignment.Repository.ItemRepository;
import com.assignment.Repository.PacketRepository;
import com.assignment.Repository.SerialNumberRepository;
import com.assignment.Service.Implementation.ItemService;
import com.assignment.Service.Implementation.PacketService;
import com.assignment.Service.Implementation.SerialNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemProcessingTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private PacketRepository packetRepository;

    @Mock
    private SerialNumberRepository serialNumberRepository;

    @InjectMocks
    private ItemService itemService;

    @InjectMocks
    private PacketService packetService;

    @Mock
    private SerialNumberService serialNumberService;

    @InjectMocks
    private SerialNumberService serialNumberServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testItemProcessing() {
        // DTO setup
        ItemDTO itemADTO = new ItemDTO("ItemA", "Item-001", 10, "Box", 50);
        ItemDTO itemBDTO = new ItemDTO("ItemB", "Item-002", 20, "Box", 50);

        // DTO to Entity Mapping
        Item itemA = new Item();
        itemA.setName("ItemA");
        itemA.setUniqueCode("Item-001");
        itemA.setQuantity(10);
        itemA.setPackingType(PackingType.valueOf("Box"));
        itemA.setPackQuantity(50);

        Item itemB = new Item();
        itemB.setName("ItemB");
        itemB.setUniqueCode("Item-002");
        itemB.setQuantity(20);
        itemB.setPackingType(PackingType.valueOf("Box"));
        itemB.setPackQuantity(50);

        // When any purchase is saved, return Item.class
        when(itemRepository.save(any(Item.class))).thenReturn(itemA).thenReturn(itemB);

        // Saving item A and generating unique packet numbers and serial numbers for the item
        itemService.savePurchase(itemADTO);
        packetService.generatePackets(itemA);

        // Saving item B and generating unique packet numbers and serial numbers for the item
        itemService.savePurchase(itemBDTO);
        packetService.generatePackets(itemB);

        // Capture all the packets saved by PacketRepository
        ArgumentCaptor<Packet> packetCaptor = ArgumentCaptor.forClass(Packet.class);
        verify(packetRepository, atLeast(1)).save(packetCaptor.capture());

        // Get the list of all captured packets
        List<Packet> capturedPackets = packetCaptor.getAllValues();

        // Now capturedPackets will contain packets for both itemA and itemB
        for (Packet packet : capturedPackets) {
            serialNumberServiceImpl.generateSerialNumbers(packet.getItem(), packet);
        }

        //itemA.getQuantity() + itemB.getQuantity() = 30
        int totalPacketsToBeGenerated = 30;

        //itemA.getPackQuantity() * itemA.getQuantity() + itemB.getPackQuantity() * itemB.getQuantity() = 1500
        int totalSerialNumbersToBeGenerated = 1500;

        // Verify the correct number of packets and serial numbers are saved
        verify(packetRepository, times(totalPacketsToBeGenerated)).save(any());
        verify(serialNumberRepository, times(totalSerialNumbersToBeGenerated)).save(any());
    }
}
