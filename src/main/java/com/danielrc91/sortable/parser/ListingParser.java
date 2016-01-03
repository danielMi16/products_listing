package com.danielrc91.sortable.parser;

import com.danielrc91.sortable.model.Listing;

public class ListingParser extends Parser<Listing> {

	@Override
	public Listing read(String line) {
		
		Listing listing = gson.fromJson(line, Listing.class);		
		return listing;
	}

}
