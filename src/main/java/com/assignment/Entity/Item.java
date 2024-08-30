package com.assignment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String name;

    private String uniqueCode;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private PackingType packingType;

    private Integer packQuantity;
}
