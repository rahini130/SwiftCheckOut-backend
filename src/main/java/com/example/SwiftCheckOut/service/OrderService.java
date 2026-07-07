package com.example.SwiftCheckOut.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.SwiftCheckOut.entity.Order;
import com.example.SwiftCheckOut.exception.OrderNotFoundException;
import com.example.SwiftCheckOut.repository.OrderRepository;


@Service
public class OrderService {

  
    private final OrderRepository orderRepo;

     public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order addOrder(Order order){
    return orderRepo.save(order);
    }

public List<Order> getAll() {
    return orderRepo.findAll();
}


public Order getById(Long id){
    return orderRepo.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found") );
}

public String updateOrder(Long id,Order o){
    Order past=orderRepo.findById(id).orElse(null);
    if(past !=null){
        past.setProductId(o.getProductId());
        past.setQuantity(o.getQuantity());
        past.setCustomer(o.getCustomer());
        past.setStatus(o.getStatus());
        orderRepo.save(past);
        return "Order added successfully";
    }
    else{
        return "Order not found";
    }
}

public Order partialUpdateOrder(Long id,Order lap) {
   Order currentVal=orderRepo.findById(id).orElse(null);
   if(currentVal !=null){
    if(currentVal.getProductId()!=null){
      currentVal.setProductId(lap.getProductId());
    }   
    if(currentVal.getQuantity()!=null){
      currentVal.setQuantity(lap.getQuantity());
    }
    if(currentVal.getCustomer()!=null){
      currentVal.setCustomer(lap.getCustomer());
    }   
    if(currentVal.getStatus()!=null){
      currentVal.setStatus(lap.getStatus());
    }
    orderRepo.save(currentVal);
    return currentVal;
}else{
    return lap;
}

}

public String deleteOrder(Long id) {
    if(orderRepo.existsById(id)){
     orderRepo.deleteById(id);
     return "deleted successfully";
    }
    return "Order not found";

}

public Order placeOrder(Order order) {

      order.setStatus("COMPLETED");
       return orderRepo.save(order);
}
}
