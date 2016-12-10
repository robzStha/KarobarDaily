package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("currencies_id")
    @Expose
    private String currenciesId;
    @SerializedName("name")
    @Expose
    private String currenciesName;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("purchase")
    @Expose
    private String purchase;
    @SerializedName("sale")
    @Expose
    private String sale;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The currenciesId
     */
    public String getCurrenciesId() {
        return currenciesId;
    }

    /**
     * @param currenciesId The currencies_id
     */
    public void setCurrenciesId(String currenciesId) {
        this.currenciesId = currenciesId;
    }

    /**
     * @return The currenciesName
     */
    public String getCurrenciesName() {
        return currenciesName;
    }

    /**
     * @param currenciesName The currencies_name
     */
    public void setCurrenciesName(String currenciesName) {
        this.currenciesName = currenciesName;
    }

    /**
     * @return The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return The purchase
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * @param purchase The purchase
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * @return The sale
     */
    public String getSale() {
        return sale;
    }

    /**
     * @param sale The sale
     */
    public void setSale(String sale) {
        this.sale = sale;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}