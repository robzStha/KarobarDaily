package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cartoon {

    @SerializedName("cartoon_id")
    @Expose
    private String cartoonId;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("imagename")
    @Expose
    private String imagename;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("language")
    @Expose
    private String language;

    /**
     * @return The cartoonId
     */
    public String getCartoonId() {
        return cartoonId;
    }

    /**
     * @param cartoonId The cartoon_id
     */
    public void setCartoonId(String cartoonId) {
        this.cartoonId = cartoonId;
    }

    /**
     * @return The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The imagename
     */
    public String getImagename() {
        return imagename;
    }

    /**
     * @param imagename The imagename
     */
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    /**
     * @return The artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist The artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}