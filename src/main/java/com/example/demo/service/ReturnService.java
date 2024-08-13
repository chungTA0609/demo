package com.example.demo.service;

import com.example.demo.entity.Return.Return;
import com.example.demo.entity.Return.ReturnItem;
import com.example.demo.entity.Return.ReturnStatus;

import java.util.List;

public interface ReturnService{
    Return initiateReturn(Long shipmentId, String reason, List<ReturnItem> returnItems);
    void updateReturnStatus(Long returnId, ReturnStatus status);
    Return trackReturn(Long returnId);
}