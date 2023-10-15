package com.example.qpassessment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ItemOrder {

    @Id
    int id;

    @Column(columnDefinition = "json")
    String items;

    @Column
    String orderedBy;

    @Column
    int orderTotal;

    @Column
    String status;
}
