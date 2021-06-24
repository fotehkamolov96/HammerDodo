package com.kamolovproducts.hammerdelivery.Home;

import com.kamolovproducts.hammerdelivery.Model.Categories;
import com.kamolovproducts.hammerdelivery.Model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();

    void hideLoading();

    void setMeal(List<Meals.Meal> meal);

    void setCategory(List<Categories.Category> category);

    void onErrorLoading(String message);
}
