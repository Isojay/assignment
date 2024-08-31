package com.assignment.Service;

import com.assignment.Entity.Item;
import com.assignment.Entity.Packet;

public interface ISerialNumberService {

    void generateSerialNumbers(Item item, Packet packet);
}
