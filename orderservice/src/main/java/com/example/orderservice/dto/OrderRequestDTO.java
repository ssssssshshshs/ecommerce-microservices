package com.example.orderservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderRequestDTO {

	@NotNull(message = "Product ID is required")
    private Long productId;
	
	 @NotNull(message = "Quantity is required")
	 @Min(1)
    private Integer quantity;
	 
	 @NotNull(message = "Price is required")
	 @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

	 @NotNull(message = "User ID is required")
	 private Long userId;
	 
	 private Long id;
	 private Double totalPrice;
	 
	 
	 
    public OrderRequestDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}