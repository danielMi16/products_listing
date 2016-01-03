package com.danielrc91.sortable.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("product_name")
	private String productName;
	private List<Listing> listing;
	
	private static Gson g = new Gson();
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<Listing> getListing() {
		return listing;
	}
	public void setListing(List<Listing> listing) {
		this.listing = listing;
	}
	
	public void addToList(Listing listing){
		if(this.listing == null)this.listing = new ArrayList<>();
		this.listing.add(listing);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (productName.compareTo(other.productName) != 0)
			return false;
		return true;
	}
	@Override
	public String toString(){
		return g.toJson(this);
	}
}
