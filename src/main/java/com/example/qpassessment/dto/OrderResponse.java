package com.example.qpassessment.dto;

import com.example.qpassessment.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    int id;
    List<Item> items;
    int total;
    String status;
}
