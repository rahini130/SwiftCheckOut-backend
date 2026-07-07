package com.example.SwiftCheckOut.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiftCheckOut.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
Optional<Inventory> findBySku(String sku);  
}