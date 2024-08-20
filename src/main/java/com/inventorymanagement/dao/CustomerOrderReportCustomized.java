package com.inventorymanagement.dao;

public interface CustomerOrderReportCustomized {

	Integer getCustomerId();

	String getUserName();

	Integer getProductId();

	String getProductName();

	Float getPrice();

	Integer getOrderedQuantity();

	Float getAmountPaid();

}
