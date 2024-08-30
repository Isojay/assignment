package com.assignment.Service;

import com.assignment.DTO.ItemDTO;
import com.assignment.Entity.Item;
import com.assignment.Entity.PackingType;
import com.assignment.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Saves an item based on the provided ItemDTO and returns the saved Item entity.
     *
     * @param itemDTO DTO containing data for the item to be purchased
     * @return The saved Item entity
     * @throws IllegalArgumentException if there is an issue with data conversion or persistence
     */
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
}
