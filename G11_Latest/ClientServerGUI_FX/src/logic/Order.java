package logic;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// check if type or order is string or enum
	private String restaurant;
	private String orderNumber;
	private String orderTime;
	private String phoneNumber;
	private String typeOfOrder;
	private String orderAddress;

	public Order(String orderNumber, String restaurant, String orderTime, String phoneNumber, String typeOfOrder,
			String orderAddress) {
		super();
		this.orderNumber = orderNumber;
		this.restaurant = restaurant;
		this.orderTime = orderTime;
		this.phoneNumber = phoneNumber;
		this.typeOfOrder = typeOfOrder;
		this.orderAddress = orderAddress;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTypeOfOrder() {
		return typeOfOrder;
	}

	public void setTypeOfOrder(String typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Override
	public String toString() {
		return restaurant + " " + orderNumber + " " + orderTime + " " + phoneNumber + " " + typeOfOrder + " "
				+ orderAddress;
	}
}