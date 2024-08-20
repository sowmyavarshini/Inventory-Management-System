package com.inventorymanagement.dao;

public interface SalesReportCustomized {

	Integer getProductId();

	String getProductName();

	String getBrand();

	String getCategory();

	Integer getStockAvailable();

	Float getPrice();

	Integer getCustomerId();

	Integer getOrderId();

	Integer getOrderedQuantity();

	Float getAmountPaid();

}
