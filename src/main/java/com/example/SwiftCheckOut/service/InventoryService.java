package com.example.SwiftCheckOut.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.SwiftCheckOut.entity.Inventory;
import com.example.SwiftCheckOut.exception.InventoryNotFoundException;
import com.example.SwiftCheckOut.repository.InventoryRepository;

@Service
public class InventoryService {
    private final InventoryRepository inRepo;

    public InventoryService(InventoryRepository inRepo) {
        this.inRepo = inRepo;
    }

    public Inventory addInventory(Inventory invevntory){
    return inRepo.save(invevntory);
    }

   public List<Inventory> getAll() {
    return inRepo.findAll();
    }


public Inventory getById(Long id){
    return inRepo.findById(id).orElseThrow(()->new InventoryNotFoundException("Inventory not found") );
}

public String updateInventory(Long id,Inventory l){
    Inventory past=inRepo.findById(id).orElse(null);
    if(past !=null){
        past.setSku(l.getSku());
        past.setStockLevel(l.getStockLevel());
        inRepo.save(past);
        return "Inventory added successfully";
    }
    else{
        return "Inventory not found";
    }
}

public Inventory partialUpdateInventory(Long id,Inventory lap) {
   Inventory currentVal=inRepo.findById(id).orElse(null);
   if(currentVal !=null){
    if(currentVal.getSku()!=null){
      currentVal.setSku(lap.getSku());
    }   
    if(currentVal.getStockLevel()!=null){
      currentVal.setStockLevel(lap.getStockLevel());
    }   
    inRepo.save(currentVal);
    return currentVal;
}else{
    return lap;
}

}

public String deleteInventory(Long id) {
    if(inRepo.existsById(id)){
     inRepo.deleteById(id);
     return "deleted successfully";
    }
    return "Inventory not found";

}

public Inventory updateStock(Long id, Integer adjustment) {
        Inventory inventory =inRepo.findById(id).orElseThrow(() ->  new RuntimeException("Inventory not found"));
        inventory.setStockLevel(inventory.getStockLevel() + adjustment);
        return inRepo.save(inventory);

    }


    public Inventory findBySku(String sku) {

        return inRepo.findBySku(sku).orElseThrow(() -> new InventoryNotFoundException("SKU not found"));

    }

}
