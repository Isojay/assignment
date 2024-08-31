package com.assignment.Service;

import com.assignment.DTO.ItemDTO;
import com.assignment.Entity.Item;

import java.util.List;

public interface IItemService {

    Item savePurchase(ItemDTO itemDTO);

    List<ItemDTO> findAll();

}
