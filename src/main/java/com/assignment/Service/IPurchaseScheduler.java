package com.assignment.Service;

import com.assignment.DTO.ItemDTO;

/**
 * The PurchaseScheduler class is responsible for processing item purchase tasks.
 * It manages the received purchase tasks in queue, processes them one by one
 * in the order they are received, and coordinates the various services needed
 * to complete the purchase.
 */
public interface IPurchaseScheduler {

    /**
     * Adds a new purchase task to the queue for processing.
     *
     * @param itemDTO The item to be purchased, encapsulated in an ItemDTO object.
     * @throws IllegalStateException if the task could not be added to the queue.
     */
    void addPurchaseToQueue(ItemDTO itemDTO);

}
