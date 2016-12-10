package com.app.karobardainik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsVideo {

@SerializedName("caption")
@Expose
private String caption;
@SerializedName("thumbnail")
@Expose
private String thumbnail;
@SerializedName("video_url")
@Expose
private String videoUrl;

/**
* 
* @return
* The caption
*/
public String getCaption() {
return caption;
}

/**
* 
* @param caption
* The caption
*/
public void setCaption(String caption) {
this.caption = caption;
}

/**
* 
* @return
* The thumbnail
*/
public String getThumbnail() {
return thumbnail;
}

/**
* 
* @param thumbnail
* The thumbnail
*/
public void setThumbnail(String thumbnail) {
this.thumbnail = thumbnail;
}

/**
* 
* @return
* The videoUrl
*/
public String getVideoUrl() {
return videoUrl;
}

/**
* 
* @param videoUrl
* The video_url
*/
public void setVideoUrl(String videoUrl) {
this.videoUrl = videoUrl;
}

}