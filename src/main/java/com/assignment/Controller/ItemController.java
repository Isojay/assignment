package com.assignment.Controller;

import com.assignment.DTO.ItemDTO;
import com.assignment.Service.ITaskScheduler;
import com.assignment.Utils.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ITaskScheduler taskScheduler;

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
}

