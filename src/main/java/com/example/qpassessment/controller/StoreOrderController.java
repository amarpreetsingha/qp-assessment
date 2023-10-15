package com.example.qpassessment.controller;

import com.example.qpassessment.dto.OrderResponse;
import com.example.qpassessment.model.Item;
import com.example.qpassessment.model.ItemOrder;
import com.example.qpassessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grocery")
public class StoreOrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("order")
    public OrderResponse createOrder(@RequestBody List<Item> request, @AuthenticationPrincipal
                                     UserDetails userDetails){
        return orderService.createOrder(request, userDetails.getUsername());
    }

    @GetMapping("order/{orderId}")
    public OrderResponse getOrder(@PathVariable int orderId, @AuthenticationPrincipal
            UserDetails userDetails){
        return orderService.getOrder(orderId, userDetails.getUsername());
    }


}
