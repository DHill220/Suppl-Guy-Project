package com.aca.supplyguy.model;

import com.aca.supplyguy.model.*;

public enum Category {
Cleaners,Dressings,Equipment,Coatings;
	
	public static Category convertStringToCategory(String value) {
		Category myCat = null;
		for(Category category: Category.values()) {
			if(category.toString().equalsIgnoreCase(value)){
				myCat = category;
				break;
			}
		}
		
		return myCat;
	}

}
