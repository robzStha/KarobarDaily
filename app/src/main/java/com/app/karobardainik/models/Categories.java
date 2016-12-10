package com.app.karobardainik.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("category")
    @Expose
    private List<Category> category = new ArrayList<Category>();

    /**
     * @return The category
     */
    public List<Category> getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(List<Category> category) {
        this.category = category;
    }

}