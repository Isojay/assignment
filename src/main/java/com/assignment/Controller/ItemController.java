package com.assignment.Controller;

import com.assignment.DTO.ItemDTO;
import com.assignment.Service.IItemService;
import com.assignment.Service.ITaskScheduler;
import com.assignment.Utils.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ITaskScheduler taskScheduler;
    private final IItemService itemService;

    @PostMapping("/purchase")
    public ResponseEntity<ResponseDTO> purchaseItem(@Valid @RequestBody ItemDTO item) {
        ResponseDTO responseDTO;
        try {

            log.info("Purchasing item {}", item);
            taskScheduler.addPurchaseToQueue(item);

            responseDTO = ResponseDTO.customResponseDTO(
                    HttpStatus.OK,
                    "Purchase initiated successfully. Notification will be sent upon the completion.",
                    null
            );

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            log.error("Error during purchase: {}", e.getMessage());

            responseDTO = ResponseDTO.customResponseDTO(
                    HttpStatus.BAD_REQUEST,
                    "Error performing the purchase.",
                    null
            );

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getItems() {
        ResponseDTO responseDTO;
        try {

            log.info("Fetching all items");
            List<ItemDTO> items = itemService.findAll();

            responseDTO = ResponseDTO.customResponseDTO(
                    HttpStatus.OK,
                    "Items fetched successfully",
                    items
            );

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            log.error("Error fetching Item List: {}", e.getMessage());

            responseDTO = ResponseDTO.customResponseDTO(
                    HttpStatus.BAD_REQUEST,
                    "Error fetching Item List.",
                    null
            );

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}

