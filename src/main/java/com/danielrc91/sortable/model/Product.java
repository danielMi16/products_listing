package com.danielrc91.sortable.model;

import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("product_name")
	private String productName;
	private String manufacturer;
	private String family;
	private String model;
	@SerializedName("announced-date")
	private String announcedDate;
	
	
	public String getProduct_name() {
		return productName;
	}
	public void setProduct_name(String product_name) {
		this.productName = product_name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAnnounced_date() {
		return announcedDate;
	}
	public void setAnnounced_date(String announced_date) {
		this.announcedDate = announced_date;
	}
	
	
}
