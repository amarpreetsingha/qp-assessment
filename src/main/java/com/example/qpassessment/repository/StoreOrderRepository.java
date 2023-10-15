package com.example.qpassessment.repository;

import com.example.qpassessment.model.Item;
import com.example.qpassessment.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOrderRepository extends JpaRepository<ItemOrder, Integer> {
}
