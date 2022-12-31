package com.educandoweb.course.entities.enums;

public enum OrderStatus {
	PAID(1),
	WAITING_PAYMENT(2),
	SHIPPED(3);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid OrderStatus code!");
	}
}
