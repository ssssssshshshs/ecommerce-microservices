package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderPatchDTO;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
   // public Order createOrder(@RequestBody Order order) 
    /*public OrderResponseDTO createOrder(@Valid
            @RequestBody OrderRequestDTO request){
    
       // return service.createOrder(order);
    	 return service.createOrder(request);
    	
    }
*/
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid
            @RequestBody OrderRequestDTO request){
    
       // return service.createOrder(order);
    	OrderResponseDTO responsedto =  service.createOrder(request);
    	return new ResponseEntity<>(responsedto, HttpStatus.CREATED);
    }
    
    
    @GetMapping
    /*public Page<OrderResponseDTO> getAllOrders(@RequestParam int page,@RequestParam int size) {

        return service.getAllOrders(page, size);
    }*/
  
    //   public List<Order> getAllOrders() {  //normal crud operations    
    
     public ResponseEntity<List<OrderResponseDTO>> getAllOrders() { 
     
       //return service.getAllOrders();
    	return ResponseEntity.ok(service.getAllOrders());
    	
    }
    
    @GetMapping("/sort")
    public ResponseEntity<List<OrderResponseDTO>> sortOrders(
            @RequestParam String field) {

        return ResponseEntity.ok(service.sortOrders(field));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> patchOrder(@PathVariable Long id, @RequestBody OrderPatchDTO request) 
    {
        return ResponseEntity.ok(service.updateOrderPartially(id, request));
    }
    
    @PutMapping("/{id}")
    public Order updateOrder(
            @PathVariable Long id,
            @RequestBody Order order) {

        return service.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {

         service.deleteOrder(id);
         return ResponseEntity.noContent().build();
    }
    
}