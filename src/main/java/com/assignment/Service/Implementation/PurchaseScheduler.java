package com.assignment.Service.Implementation;

import com.assignment.DTO.ItemDTO;
import com.assignment.Entity.Item;
import com.assignment.Service.IItemService;
import com.assignment.Service.INotificationService;
import com.assignment.Service.IPacketService;
import com.assignment.Service.IPurchaseScheduler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The PurchaseScheduler class is responsible for processing item purchase tasks.
 * It manages the received purchase tasks in queue, processes them one by one
 * in the order they are received, and coordinates the various services needed
 * to complete the purchase.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseScheduler implements IPurchaseScheduler {

    private final IItemService itemService;
    private final IPacketService packetService;
    private final INotificationService notificationService;

    // Queue to hold purchase tasks to be processed
    private final BlockingQueue<List<ItemDTO>> taskQueue = new LinkedBlockingQueue<>();

    // Executor service to manage the asynchronous processing of the task queue
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * Initializes the PurchaseScheduler by starting the queue processing
     * task in a separate thread.
     * <p>
     * This method is automatically called after the bean is created
     * and dependencies are injected.
     */
    @PostConstruct
    public void init() {
        executorService.submit(this::processQueue);
    }

    /**
     * Adds a new purchase task to the queue for processing.
     *
     * @param itemDTO The item to be purchased, encapsulated in an ItemDTO object.
     * @throws IllegalStateException if the task could not be added to the queue.
     */
    @Override
    public void addPurchaseToQueue(List<ItemDTO> itemDTO) {
        boolean status = taskQueue.offer(itemDTO);
        if (status) {
            log.info("Purchase added to queue.");
        } else {
            log.warn("Purchase was not added to queue.");
            throw new IllegalStateException("Failed to add item to the queue.");
        }
    }

    /**
     * Continuously processes the queue of purchase tasks.
     * <p>
     * This method runs in a separate thread and processes each item
     * in the queue. It also handles interruptions and logs errors
     * that occur during processing.
     */
    private void processQueue() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Take the next item from the queue for processing
                List<ItemDTO> itemDTOList = taskQueue.take();

                // Send notification that processing has started
                notificationService.sendNotification("Purchase processing initiated : " ,null);

                for(ItemDTO itemDTO : itemDTOList) {

                    // Perform the purchase processing
                    serviceCall(itemDTO);
                }

                // Send notification that processing has completed
                notificationService.sendNotification("Purchase processed. " ,null);
            } catch (InterruptedException e) {
                // Handle thread interruption
                Thread.currentThread().interrupt();
                log.error("Queue processing was interrupted: {}", e.getMessage());
            } catch (Exception e) {
                // Handle any other exceptions during processing
                log.error("Error processing item: {}", e.getMessage());
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    /**
     * Executes the core business logic for processing a purchase.
     * <p>
     * This method calls the relevant services to:
     * 1. Save the purchase information.
     * 2. Generate packets number related to the item.
     * 3. Generate serial numbers for the item.
     *
     * @param itemDTO The data transfer object containing item information.
     */
    private void serviceCall(ItemDTO itemDTO) {
        try {
            // Save the purchase and obtain the persisted Item entity
            Item item = itemService.savePurchase(itemDTO);

            // Generate packets numbers associated with the item
            packetService.generatePackets(item);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }



    }
}
