package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuCategory {

@SerializedName("category")
@Expose
private HomeCategory category;

/**
* 
* @return
* The category
*/
public HomeCategory getCategory() {
return category;
}

/**
* 
* @param category
* The category
*/
public void setCategory(HomeCategory category) {
this.category = category;
}

}