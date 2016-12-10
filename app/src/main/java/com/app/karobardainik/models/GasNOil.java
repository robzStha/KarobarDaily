package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GasNOil {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     * The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}