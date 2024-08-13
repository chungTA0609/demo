package com.example.demo.controller.back;

import com.example.demo.dto.ReturnDTO;
import com.example.demo.dto.ReturnRequestDTO;
import com.example.demo.dto.ReturnStatusUpdateRequestDTO;
import com.example.demo.entity.Return.Return;
import com.example.demo.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/returns")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PostMapping
    public ResponseEntity<Return> initiateReturn(@RequestBody ReturnRequestDTO request) {
        Return initReturn =  returnService.initiateReturn(request.getShipmentId(), request.getReason(), request.getReturnItems());
        return ResponseEntity.status(HttpStatus.CREATED).body(initReturn);
    }

    @PutMapping("/{returnId}/status")
    public ResponseEntity<Void> updateReturnStatus(@PathVariable Long returnId, @RequestBody ReturnStatusUpdateRequestDTO request) {
        returnService.updateReturnStatus(returnId, request.getStatus());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{returnId}")
    public ResponseEntity<Return>  trackReturn(@PathVariable Long returnId) {
        Return trackReturn = returnService.trackReturn(returnId);
        return ResponseEntity.status(HttpStatus.OK).body(trackReturn);
    }
}
