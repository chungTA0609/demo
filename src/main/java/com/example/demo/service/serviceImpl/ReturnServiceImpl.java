package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Order;
import com.example.demo.entity.Return;
import com.example.demo.repository.ReturnRepository;
import com.example.demo.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private ReturnRepository returnRepository;

    @Override
    public Long createReturn(Long orderId, String reason) {
        Return returnOrder = new Return();
        returnOrder.setOrder(new Order(orderId));
        returnOrder.setReturnDate(LocalDateTime.now());
        returnOrder.setStatus("Pending");
        returnOrder.setReason(reason);
        returnRepository.save(returnOrder);
        return returnOrder.getReturnId();
    }

    @Override
    public void processReturn(Long returnId, String status) {
        Return returnOrder = returnRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        returnOrder.setStatus(status);
        if ("Completed".equals(status)) {
            returnOrder.setReturnDate(LocalDateTime.now());
        }
        returnRepository.save(returnOrder);
    }

    @Override
    public String checkReturnStatus(Long returnId) {
        Return returnOrder = returnRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        return returnOrder.getStatus();
    }
}
