package com.app.karobardainik.server_protocols;

import com.app.karobardainik.models.Article;
import com.app.karobardainik.models.Categories;
import com.app.karobardainik.models.HomeCategory;
import com.app.karobardainik.models.HomeResponse;
import com.app.karobardainik.models.MenuCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bugatti on 22/11/16.
 */

public interface ApiCalls {

    @GET("menu_category")
    Call<Categories> getMenuCategories();


    @GET("home")
    Call<HomeResponse> getHomeResponse();

    @GET("category/{id}")
    Call<MenuCategory> getCategoriesNewsById(@Path("id") long id);

}
