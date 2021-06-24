package com.kamolovproducts.hammerdelivery.api;


import com.kamolovproducts.hammerdelivery.Model.Categories;
import com.kamolovproducts.hammerdelivery.Model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

}
