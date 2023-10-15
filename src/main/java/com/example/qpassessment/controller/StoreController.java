package com.example.qpassessment.controller;

import com.example.qpassessment.model.Item;
import com.example.qpassessment.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grocery")
public class StoreController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("items")
    public void createItem(@RequestBody Item item){
        inventoryService.addItem(item);
    }

    @PutMapping("items/{name}")
    public void modifyItem(@RequestBody Item item, @PathVariable String name){
        inventoryService.modifyItem(item, name);
    }

    @DeleteMapping("items/{name}")
    public void deleteItem(@PathVariable String name){
        inventoryService.deleteItem(name);
    }

    @GetMapping("items")
    public List<Item> getAllItems(){
        return inventoryService.findItems(null);
    }

    @GetMapping("items/{name}")
    public Item getItem(@PathVariable String name){
        return inventoryService.findItems(name).get(0);
    }

}
