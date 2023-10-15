package com.example.qpassessment.service;

import com.example.qpassessment.dto.OrderResponse;
import com.example.qpassessment.exception.NotFoundException;
import com.example.qpassessment.model.Item;
import com.example.qpassessment.model.ItemOrder;
import com.example.qpassessment.model.OrderStatus;
import com.example.qpassessment.repository.StoreOrderCustomRepository;
import com.example.qpassessment.repository.StoreOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@Service
@Slf4j
public class OrderService {

    @Autowired
    StoreOrderCustomRepository customRepository;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    StoreOrderRepository orderRepository;
    Random random = new Random();

    Predicate<List<Item>> validateItems = items ->
            inventoryService.findItems(null).containsAll(items);

    public OrderResponse createOrder(List<Item> request, String user) {
        if(!validateItems.test(request))
            throw new NotFoundException("Item not found");
        ItemOrder itemOrder = buildOrderItem(request, user);
        orderRepository.save(itemOrder);
        log.info("Order created with order id " + itemOrder.getId());
        return new OrderResponse(itemOrder.getId(), request,
                itemOrder.getOrderTotal(), itemOrder.getStatus());
    }

    public OrderResponse getOrder(int id, String user) {
        ItemOrder order = customRepository.getItemOrder(id, user);
        if(null == order)
            throw new NotFoundException("Order not found");
        List<Item> items = OrderUtility.convertToObject(order.getItems());
        log.info("Order retrieved successfully with id " + id);
        return new OrderResponse(id, items, order.getOrderTotal(),
                order.getStatus());

    }

    private ItemOrder buildOrderItem(List<Item> request, String user){
        log.info("Building order item");
        ItemOrder itemOrder = new ItemOrder();
        int ordertotal = 0;
        String json = OrderUtility.convertToJson(request);
        ordertotal = request.stream()
                .mapToInt(Item::getPrice).reduce(Integer::sum).getAsInt();
        itemOrder.setOrderTotal(ordertotal);
        itemOrder.setItems(json);
        itemOrder.setId(random.nextInt(1000));
        itemOrder.setStatus(OrderStatus.APPROVED.name());
        itemOrder.setOrderedBy(user);
        return itemOrder;
    }

}
