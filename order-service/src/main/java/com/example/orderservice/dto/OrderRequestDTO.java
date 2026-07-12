package com.example.orderservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor



public class OrderRequestDTO {

	@NotNull(message = "Product ID is required")
    private Integer productId;
	
	 @NotNull(message = "Quantity is required")
	 @Min(1)
    private Integer quantity;
	 @NotNull(message = "User ID is required")
	 private Integer userId;
	 
	 private Integer id;
	 private Double totalPrice;
	 
	 /*
	 
    public OrderRequestDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


 */

}
