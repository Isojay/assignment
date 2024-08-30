package com.assignment.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "serial_number")
public class SerialNumber implements Serializable {

    @Id
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "packet_id", referencedColumnName = "packetNumber")
    private Packet packet;

}
