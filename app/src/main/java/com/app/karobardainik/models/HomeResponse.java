package com.app.karobardainik.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeResponse {

    @SerializedName("special_news")
    @Expose
    private List<SpecialNews> specialNews = new ArrayList<SpecialNews>();
    @SerializedName("category")
    @Expose
    private List<HomeCategory> category = new ArrayList<>();
    @SerializedName("video")
    @Expose
    private List<NewsVideo> newsVideos = new ArrayList<NewsVideo>();
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies = new ArrayList<Currency>();
    @SerializedName("bullion")
    @Expose
    private List<Bullion> bullion = new ArrayList<Bullion>();
    @SerializedName("gas_n_oil")
    @Expose
    private List<GasNOil> gasNOil = new ArrayList<GasNOil>();
    @SerializedName("cartoon")
    @Expose
    private List<Cartoon> cartoon = new ArrayList<Cartoon>();

    /**
     * @return The specialNews
     */
    public List<SpecialNews> getSpecialNews() {
        return specialNews;
    }

    /**
     * @param specialNews The special_news
     */
    public void setSpecialNews(List<SpecialNews> specialNews) {
        this.specialNews = specialNews;
    }

    /**
     * @return The category
     */
    public List<HomeCategory> getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(List<HomeCategory> category) {
        this.category = category;
    }

    /**
     * @return The currencies
     */
    public List<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * @param currencies The currencies
     */
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * @return The bullion
     */
    public List<Bullion> getBullion() {
        return bullion;
    }

    /**
     * @param bullion The bullion
     */
    public void setBullion(List<Bullion> bullion) {
        this.bullion = bullion;
    }

    /**
     * @return The gasNOil
     */
    public List<GasNOil> getGasNOil() {
        return gasNOil;
    }

    /**
     * @param gasNOil The gas_n_oil
     */
    public void setGasNOil(List<GasNOil> gasNOil) {
        this.gasNOil = gasNOil;
    }

    /**
     * @return The cartoon
     */
    public List<Cartoon> getCartoon() {
        return cartoon;
    }

    /**
     * @param cartoon The cartoon
     */
    public void setCartoon(List<Cartoon> cartoon) {
        this.cartoon = cartoon;
    }

    public List<NewsVideo> getNewsVideos() {
        return newsVideos;
    }

    public void setNewsVideos(List<NewsVideo> newsVideos) {
        this.newsVideos = newsVideos;
    }
}