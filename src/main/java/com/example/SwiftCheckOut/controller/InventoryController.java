package com.example.SwiftCheckOut.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiftCheckOut.entity.Inventory;
import com.example.SwiftCheckOut.service.InventoryService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/incontrol")
public class InventoryController {
    @Autowired
    InventoryService inService;

        @PostMapping("/addInventory")
        @PreAuthorize("hasRole('ADMIN')")
        public Inventory addInventory(@Valid @RequestBody Inventory inventory){
            return inService.addInventory(inventory);
        }

        @GetMapping("/getAllInventory")
        public List<Inventory> getAll(){
            return inService.getAll();
        } 

        @PutMapping("/stock/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Inventory> updateStock(@PathVariable Long id,@RequestBody(required = false) Map<String,Integer> body) {
        Integer quantity = 0;
        if(body != null && body.containsKey("quantity")) {
            quantity = body.get("quantity");

        }

        Inventory updated =inService.updateStock(id, quantity);
        return ResponseEntity.ok(updated);

    }

       @GetMapping("/inventory/{id}")
       public Inventory getById(@PathVariable Long id){
        return inService.getById(id);
       }

       @PutMapping("/update/{id}")
        public String updateLaptop(@PathVariable Long id,@RequestBody Inventory l){
            return inService.updateInventory(id,l);
        }

        @PatchMapping("/partialUpdate/{id}")
        public Inventory partialUpdateInventory(@PathVariable Long id,@RequestBody Inventory lap){
            return inService.partialUpdateInventory(id,lap);
        }

        @DeleteMapping("/delete/{id}")
        public String deleteInventory(@PathVariable Long id){
            return inService.deleteInventory(id);
        }
        
       
}
