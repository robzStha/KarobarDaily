package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("newsarticle_id")
    @Expose
    private String newsarticleId;
    @SerializedName("publishdate")
    @Expose
    private String publishdate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("reporter")
    @Expose
    private String reporter;
    @SerializedName("featureimage")
    @Expose
    private String featureimage;

    /**
     * @return The newsarticleId
     */
    public String getNewsarticleId() {
        return newsarticleId;
    }

    /**
     * @param newsarticleId The newsarticle_id
     */
    public void setNewsarticleId(String newsarticleId) {
        this.newsarticleId = newsarticleId;
    }

    /**
     * @return The publishdate
     */
    public String getPublishdate() {
        return publishdate;
    }

    /**
     * @param publishdate The publishdate
     */
    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle The subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The reporter
     */
    public String getReporter() {
        return reporter;
    }

    /**
     * @param reporter The reporter
     */
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    /**
     * @return The featureimage
     */
    public String getFeatureimage() {
        return featureimage;
    }

    /**
     * @param featureimage The featureimage
     */
    public void setFeatureimage(String featureimage) {
        this.featureimage = featureimage;
    }

}