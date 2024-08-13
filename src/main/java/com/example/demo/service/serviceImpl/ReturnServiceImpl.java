package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Order.Order;
import com.example.demo.entity.Return.Return;
import com.example.demo.entity.Return.ReturnItem;
import com.example.demo.entity.Return.ReturnReason;
import com.example.demo.entity.Return.ReturnStatus;
import com.example.demo.entity.Shipment.Shipment;
import com.example.demo.repository.ReturnReasonRepository;
import com.example.demo.repository.ReturnRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ReturnService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ReturnReasonRepository returnReasonRepository;

    @Transactional
    public Return initiateReturn(Long shipmentId, String reason, List<ReturnItem> returnItems) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));

        ReturnReason returnReason = returnReasonRepository.findByReason(reason);

        Return aReturn = new Return();
        aReturn.setShipment(shipment);
        aReturn.setReturnDate(LocalDateTime.now());
        aReturn.setStatus(ReturnStatus.PENDING);
        aReturn.setReturnReason(returnReason);

        aReturn.setReturnItems(returnItems);
        for (ReturnItem item : returnItems) {
            item.setAReturn(aReturn);
        }

        return returnRepository.save(aReturn);
    }

    @Transactional
    public void updateReturnStatus(Long returnId, ReturnStatus status) {
        Return aReturn = returnRepository.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("Return not found"));

        aReturn.setStatus(status);
        returnRepository.save(aReturn);
    }

    @Transactional()
    public Return trackReturn(Long returnId) {
        return returnRepository.findById(returnId)
                .orElseThrow(() -> new IllegalArgumentException("Return not found"));
    }
}
