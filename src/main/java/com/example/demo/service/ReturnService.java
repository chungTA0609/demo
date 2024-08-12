package com.example.demo.service;

public interface ReturnService{
     Long createReturn(Long id, String reason);
     void processReturn(Long id, String reason);
     String checkReturnStatus(Long id);

}