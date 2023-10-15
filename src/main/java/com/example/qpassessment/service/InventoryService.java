package com.example.qpassessment.service;

import com.example.qpassessment.exception.NotFoundException;
import com.example.qpassessment.model.Item;
import com.example.qpassessment.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    StoreRepository storeRepository;

    public void addItem(Item item) {
        storeRepository.save(item);
    }

    public void modifyItem(Item item, String name){
        if( storeRepository.findById(name).isEmpty()){
            throw new NotFoundException("Item not found");
        }
        log.info("Modified item " + name);
        storeRepository.save(item);
    }

    public Optional<Item> findItemById(String name){
        return storeRepository.findById(name);
    }

    public void deleteItem(String name){
        Optional<Item> item = findItemById(name);
        if( item.isEmpty()){
            throw new NotFoundException("Item not found");
        }
        log.info("Item deleted successfully " + name);
        storeRepository.delete(item.get());
    }

    public List<Item> findItems(String name) {
        if( null == name){
            return storeRepository.findAll();
        }
        else {
            Optional<Item> item = storeRepository.findById(name);
            return item.map(Collections::singletonList).orElseThrow(
                    () -> new NotFoundException("Item not found")
            );
        }
    }

}
