package com.assignment.DTO;


import lombok.Data;

@Data
public class ItemDTO {

    private String name;

    private String uniqueCode;

    private Integer quantity;

    private String packingType;

    private Integer packQuantity;

    public ItemDTO(String name, String uniqueCode, Integer quantity, String packingType, Integer packQuantity) {
        this.name = name;
        this.uniqueCode = uniqueCode;
        this.quantity = quantity;
        this.packingType = packingType;
        this.packQuantity = packQuantity;
    }
}
