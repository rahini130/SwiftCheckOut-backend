package com.example.SwiftCheckOut.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiftCheckOut.entity.Customer;

import com.example.SwiftCheckOut.service.CustomerService;
import com.example.SwiftCheckOut.service.JwtService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth/authcontrol")
public class AuthController {
  
 private final CustomerService cusservice;
 private final JwtService jwtService;
 

    public AuthController(CustomerService cusservice, JwtService jwtService) {
    this.cusservice = cusservice;
    this.jwtService=jwtService;
}

    @PostMapping("/addCustomer")
        public Customer addCustomer(@Valid @RequestBody Customer customer){
            return cusservice.addCustomer(customer);
        }

        @GetMapping("/getAllCustomer")
        public List<Customer> getAll(){
            return cusservice.getAll();
        }

       @GetMapping("/customer/{id}")
       public Customer getById(@PathVariable Long id){
        return cusservice.getById(id);
       }

       @PutMapping("/update/{id}")
        public String updateCustomer(@PathVariable Long id,@RequestBody Customer c){
            return cusservice.updateCustomer(id,c);
        }

        @PatchMapping("/partialUpdate/{id}")
        public Customer partialUpdateCustomer(@PathVariable Long id,@RequestBody Customer lap){
            return cusservice.partialUpdateCustomer(id,lap);
        }

        @DeleteMapping("/delete/{id}")
        public String deleteCustomer(@PathVariable Long id){
            return cusservice.deleteCustomer(id);
        }


         @PostMapping("/passEncrypt")
        public String passwordEncrypt(@RequestParam("p") String pass){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.encode(pass);    
        }

        @PostMapping("/register")
        public ResponseEntity<Customer> register(@RequestBody Customer customer) {

        Optional<Customer> existing =cusservice.findByEmail(customer.getEmail());

        if (existing.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

        Customer saved = cusservice.register(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Customer customer) {
    try {
        Customer user = cusservice.login(customer.getEmail(),customer.getPassword());
        String token = jwtService.generateToken(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole().name());
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
 }

}

    