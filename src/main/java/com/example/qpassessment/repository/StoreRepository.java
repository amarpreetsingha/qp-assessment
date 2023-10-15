package com.example.qpassessment.repository;

import com.example.qpassessment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Item, String> {
}
