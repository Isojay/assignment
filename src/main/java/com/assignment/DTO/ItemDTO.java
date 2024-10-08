package com.assignment.DTO;

import com.assignment.Entity.PackingType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Unique code is mandatory")
    private String uniqueCode;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private PackingType packingType;

    @Min(value = 1, message = "Pack quantity must be at least 1")
    private Integer packQuantity;

    private String email;

    public ItemDTO() {
    }

    public ItemDTO(String name, String uniqueCode, Integer quantity, String packingType, Integer packQuantity) {
        this.name = name;
        this.uniqueCode = uniqueCode;
        this.quantity = quantity;
        this.packingType = PackingType.valueOf(packingType);
        this.packQuantity = packQuantity;
    }
}
