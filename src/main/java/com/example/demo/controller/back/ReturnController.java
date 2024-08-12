package com.example.demo.controller.back;

import com.example.demo.dto.ReturnDTO;
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
    public ResponseEntity<Long> createReturn(@RequestBody ReturnDTO returnDTO) {
        Long returnId = returnService.createReturn(returnDTO.getOrderId(), returnDTO.getReason());
        return ResponseEntity.status(HttpStatus.CREATED).body(returnId);
    }

    @PutMapping("/{returnId}/process")
    public ResponseEntity<Void> processReturn(@PathVariable Long returnId, @RequestBody String status) {
        returnService.processReturn(returnId, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{returnId}/status")
    public ResponseEntity<String> checkReturnStatus(@PathVariable Long returnId) {
        String status = returnService.checkReturnStatus(returnId);
        return ResponseEntity.ok(status);
    }
}
