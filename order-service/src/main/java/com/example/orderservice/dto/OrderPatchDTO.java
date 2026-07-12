package com.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class OrderPatchDTO {


	private Double price;
	private Integer quantity;
	private Integer userId;
	private Integer productId;
	private Double totalPrice;

	/*

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	*/
	
}
