package com.example.SwiftCheckOut.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.SwiftCheckOut.entity.Customer;

import com.example.SwiftCheckOut.exception.InventoryNotFoundException;
import com.example.SwiftCheckOut.repository.CustomerRepository;

@Service
public class CustomerService {
  
  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  public CustomerService(CustomerRepository customerRepository,PasswordEncoder passwordEncoder) {
    this.customerRepository = customerRepository;
   this.passwordEncoder=passwordEncoder;
  }

 public Customer addCustomer(Customer customer){
    return customerRepository.save(customer);
    }

public List<Customer> getAll() {
    return customerRepository.findAll();
}


public Customer getById(Long id){
    return customerRepository.findById(id).orElseThrow(()->new InventoryNotFoundException("Cusotmer not found") );
}

public String updateCustomer(Long id,Customer c){
    Customer past=customerRepository.findById(id).orElse(null);
    if(past !=null){
        past.setEmail(c.getEmail());
        past.setPassword(c.getPassword());
        past.setUsername(c.getUsername());
        past.setRole(c.getRole());
        customerRepository.save(past);
        return "Customer added successfully";
    }
    else{
        return "Customer not found";
    }
}

public Customer partialUpdateCustomer(Long id,Customer lap) {
   Customer currentVal=customerRepository.findById(id).orElse(null);
   if(currentVal !=null){
    if(currentVal.getEmail()!=null){
      currentVal.setEmail(lap.getEmail());
    }   
    if(currentVal.getPassword()!=null){
      currentVal.setPassword(lap.getPassword());
    }  
    if(currentVal.getUsername()!=null){
      currentVal.setUsername(lap.getUsername());
    }  
    if(currentVal.getRole()!=null){
      currentVal.setRole(lap.getRole());
    }   
    customerRepository.save(currentVal);
    return currentVal;
}else{
    return lap;
}

}

public String deleteCustomer(Long id) {
    if(customerRepository.existsById(id)){
     customerRepository.deleteById(id);
     return "deleted successfully";
    }
    return "Customer not found";

}
  

 public Customer register(Customer c) {

    if (customerRepository.existsByEmail(c.getEmail())) {
        throw new IllegalStateException("Email already in use");
    }


    Customer customer = new Customer();

    customer.setEmail(c.getEmail());
    customer.setUsername(c.getUsername());
    customer.setRole(c.getRole());
    customer.setPassword(passwordEncoder.encode(c.getPassword()));

    Customer savedCustomer = customerRepository.save(customer);
    return savedCustomer;
}

public Optional<Customer> findByEmail(String email) {

    return customerRepository.findByEmail(email);

}

public Customer login(String email, String password) {
    Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));

    if(!passwordEncoder.matches(password,customer.getPassword())) {
       throw new RuntimeException("Invalid password");
    }
    return customer;

}
}
 

