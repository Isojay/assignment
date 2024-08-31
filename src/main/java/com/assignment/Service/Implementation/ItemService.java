package com.assignment.Service.Implementation;

import com.assignment.DTO.ItemDTO;
import com.assignment.Entity.Item;
import com.assignment.Entity.PackingType;
import com.assignment.Repository.ItemRepository;
import com.assignment.Service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

    private final ItemRepository itemRepository;

    /**
     * Saves an item based on the provided ItemDTO and returns the saved Item entity.
     *
     * @param itemDTO DTO containing data for the item to be purchased
     * @return The saved Item entity
     * @throws IllegalArgumentException if there is an issue with data conversion or persistence
     */
    @Override
    @Transactional
    public Item savePurchase(ItemDTO itemDTO) {
        // Create a new Item instance
        Item item = new Item();

        try {
            // Set the properties of the item from the DTO
            item.setName(itemDTO.getName());
            item.setUniqueCode(itemDTO.getUniqueCode());
            item.setQuantity(itemDTO.getQuantity());

            item.setPackingType(PackingType.valueOf(itemDTO.getPackingType()));
            item.setPackQuantity(itemDTO.getPackQuantity());

            return itemRepository.save(item);
        } catch (IllegalArgumentException e) {
            // Handle specific issues related to invalid packing type or other conversion errors
            throw new IllegalArgumentException("Error processing packing type or other data: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle any other exceptions during the save operation
            throw new IllegalArgumentException("Error saving item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ItemDTO> findAll() {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> itemDTOs = new ArrayList<>();
            for (Item item : items) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setId(item.getId());
                itemDTO.setName(item.getName());
                itemDTO.setUniqueCode(item.getUniqueCode());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPackingType(item.getPackingType().name());
                itemDTO.setPackQuantity(item.getPackQuantity());
                itemDTOs.add(itemDTO);
            }
            return itemDTOs;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}
