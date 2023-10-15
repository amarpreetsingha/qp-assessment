package com.example.qpassessment.repository;

import com.example.qpassessment.exception.NotFoundException;
import com.example.qpassessment.model.ItemOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class StoreOrderCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ItemOrder getItemOrder(int id, String user){
        try {
            return (ItemOrder) entityManager
                    .createQuery("FROM ItemOrder i WHERE i.id = :id and i.orderedBy = :name")
                    .setParameter("id", id)
                    .setParameter("name", user)
                    .getSingleResult();
        } catch( NoResultException ex){
            throw new NotFoundException("Order not found");
        }
    }
}
