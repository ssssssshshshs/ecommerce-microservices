package com.example.orderservice.service;


import com.example.orderservice.client.ProductFeignClient;
import com.example.orderservice.dto.OrderPatchDTO;
import com.example.orderservice.dto.OrderRequestDTO;

import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.dto.ProductResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.InvalidOrderException;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
	@Autowired
    private OrderRepository repository;

   // public Order createOrder(Order order)
    public OrderResponseDTO createOrder(OrderRequestDTO request){

        
        if(request.getQuantity() <= 0) {

        	 logger.warn("Invalid quantity entered: {}",request.getQuantity());
            throw new InvalidOrderException(
                    "Quantity must be greater than zero");
        }

        if(request.getProductId() <= 0) {
        	logger.warn("Invalid ProductId entered: {}",request.getProductId());
            throw new InvalidOrderException("Invalid product id");
        }

        if(request.getUserId() <= 0) {
        	logger.warn("Invalid userId entered: {}",request.getUserId());
            throw new InvalidOrderException("Invalid user id");
        }
        logger.info("Creating new order");
    	Order order = new Order();

    	order.setProductId(request.getProductId());
    	order.setQuantity(request.getQuantity());
    	order.setPrice(request.getPrice());
        double total = order.getPrice() * order.getQuantity();
        order.setTotalPrice(total);
        order.setUserId(request.getUserId());
        
        logger.info("Saving order to database");
        Order savedOrder = repository.save(order);
        logger.info("Order placed successfully");

        // entity-> Response DTO layer...........

        OrderResponseDTO response = new OrderResponseDTO();

        response.setId(savedOrder.getId());
        response.setProductId(savedOrder.getProductId());
        response.setQuantity(savedOrder.getQuantity());
        response.setPrice(savedOrder.getPrice());
        response.setTotalPrice(savedOrder.getTotalPrice());
        //    return repository.save(order);
        
        response.setUserId(request.getUserId());
        return response;
    }

    /*public List<Order> getAllOrders() {
        return repository.findAll();
    }*/
    
    
        public List<OrderResponseDTO> getAllOrders() {
   	 
        	logger.info("Fetching all orders");
        List<Order> orders = repository.findAll();
        logger.info("Total orders fetched: {}",orders.size());
        
        List<OrderResponseDTO> responseList = new ArrayList<>();

        for (Order order : orders) {

            OrderResponseDTO response = new OrderResponseDTO();

            response.setId(order.getId());
            response.setProductId(order.getProductId());
            response.setQuantity(order.getQuantity());
            response.setPrice(order.getPrice());
            response.setTotalPrice(order.getTotalPrice());
            response.setUserId(order.getUserId());
            responseList.add(response);
        }
        logger.info("Returning all orders");
        return responseList;
    }
      
    public Page<OrderResponseDTO> getAllOrders(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = repository.findAll(pageable);

        return orders.map(order -> { 
            OrderResponseDTO response = new OrderResponseDTO();

            response.setId(order.getId());
            response.setQuantity(order.getQuantity());
            response.setPrice(order.getPrice());
            response.setTotalPrice(order.getTotalPrice());
            response.setUserId(order.getUserId());
            return response;
        });
    }

    public List<OrderResponseDTO> sortOrders(String field) {

       // List<Order> orders = repository.findAll(Sort.by(field));
    	List<Order> orders =repository.findAll(Sort.by(field).descending());
        List<OrderResponseDTO> responseList = new ArrayList<>();

        for(Order order : orders) {

            OrderResponseDTO response = new OrderResponseDTO();

            response.setId(order.getId());
            response.setQuantity(order.getQuantity());
            response.setPrice(order.getPrice());
            response.setTotalPrice(order.getTotalPrice());
            response.setUserId(order.getUserId());
            responseList.add(response);
        }

        return responseList;
    }
    public OrderResponseDTO getOrderById(Long id) {

    	 logger.info("Fetching order by id: {}", id);
    	   Order order = repository.findById(id)
    	            .orElseThrow(() -> {

          logger.error("Order not found with id: {}",id);
   	      return new OrderNotFoundException("Order not found with id "+id);
    	            });
    	  logger.error("Order not found");
    	  
    	OrderResponseDTO response = new OrderResponseDTO();
    	response.setId(order.getId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setPrice(order.getPrice());
        response.setTotalPrice(order.getTotalPrice());
        response.setUserId(order.getUserId());
        return response;
    }
    
  public OrderResponseDTO updateOrderPartially(Long id, OrderPatchDTO request)
  {
	  Order order=repository.findById(id)
			  .orElseThrow(()-> new OrderNotFoundException ("Order not found with id : "+id));
	  
	  if(request.getPrice()!=null)
	  {
		  order.setPrice(request.getPrice());
	  }
	  if(request.getUserId()!=null)
	  {
		  order.setUserId(request.getUserId());
	  }
	  if(request.getProductId()!=null)
	  {
		  order.setProductId(request.getProductId());
	  }
	  if(request.getQuantity()!=null)
	  {
		  order.setQuantity(request.getQuantity());
	  }

	 // order.setTotalPrice(request.getTotalPrice());

      if(order.getPrice() != null && order.getQuantity() != null)
      {
          order.setTotalPrice(order.getPrice() * order.getQuantity());
      }



	  Order saveOrder=repository.save(order);


	  OrderResponseDTO response=new OrderResponseDTO();
	  
	  response.setPrice(saveOrder.getPrice());
	  response.setUserId(saveOrder.getUserId());
	  response.setQuantity(saveOrder.getQuantity());
	  response.setProductId(saveOrder.getProductId());
	  response.setTotalPrice(saveOrder.getTotalPrice());
	  
	  
	  
	  return response;
  }
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public OrderResponseDTO updateOrderPartially(Long id, OrderPatchDTO request) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found with id: " + id));

        if (request.getProductId() != null) {
            order.setProductId(request.getProductId());
        }

        if (request.getQuantity() != null) {
            order.setQuantity(request.getQuantity());
        }

        if (request.getPrice() != null) {
            order.setPrice(request.getPrice());
        }

        if (request.getUserId() != null) {
            order.setUserId(request.getUserId());
        }

        // Recalculate total price
        order.setTotalPrice(order.getPrice() * order.getQuantity());

        Order savedOrder = repository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();

        response.setId(savedOrder.getId());
        response.setProductId(savedOrder.getProductId());
        response.setQuantity(savedOrder.getQuantity());
        response.setPrice(savedOrder.getPrice());
        response.setUserId(savedOrder.getUserId());
        response.setTotalPrice(savedOrder.getTotalPrice());

        return response;
    }
    */
    public Order updateOrder(Long id, Order updatedOrder) {

    //    Order existing = repository.findById(id)
      //          .orElseThrow(() -> new RuntimeException("Order not found"));

       
 	   Order existing = repository.findById(id)
 	            .orElseThrow(() -> {
      return new OrderNotFoundException("Order not found with id "+id);
 	            });
 	  
        
        
        existing.setProductId(updatedOrder.getProductId());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setPrice(updatedOrder.getPrice());

        double total = updatedOrder.getPrice() * updatedOrder.getQuantity();

        existing.setTotalPrice(total);
        existing.setUserId(updatedOrder.getUserId());
        return repository.save(existing);
    }
/*
    public String deleteOrder(Long id) {

        repository.deleteById(id);
        return "Order deleted successfully";    
    }*/
    
    public String deleteOrder(Long id) {

        if (!repository.existsById(id)) {
            throw new OrderNotFoundException(
                    "Order not found with id: " + id);
        }

        repository.deleteById(id);

        return "Order deleted successfully";
    }



    @Autowired
    private ProductFeignClient productFeignClient;

    public ProductResponseDTO getProduct(
            Integer id) {

        return productFeignClient.getProduct(id);
    }

}