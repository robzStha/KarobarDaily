package com.app.karobardainik.utils;

import android.app.Activity;
import android.content.Intent;

import com.app.karobardainik.activities.Home;
import com.app.karobardainik.activities.WebViewActivity;

/**
 * Created by Robz on 6/29/2016.
 */
public class Opener {
    static Intent i;

    public static void BaseActivity(Activity activity) {
        startActivity(activity, Home.class);
    }

    /**
     * Starts the given activity
     *
     * @param activity      : instance of the current activity
     * @param activityClass : Activity to be opened
     */
    private static void startActivity(Activity activity, Class<?> activityClass) {
        i = new Intent(activity, activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(i);
    }

    public static void WebViewActivity(Activity activity, String newsarticleId, String newsTitle) {
        i = new Intent(activity, WebViewActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("article_id", newsarticleId);
        i.putExtra("article_title", newsTitle);
        activity.startActivity(i);
    }

}