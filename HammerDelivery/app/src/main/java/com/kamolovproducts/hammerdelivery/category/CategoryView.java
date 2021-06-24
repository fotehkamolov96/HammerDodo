package com.kamolovproducts.hammerdelivery.category;

import com.kamolovproducts.hammerdelivery.Model.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();

    void hideLoading();

    void setMeals(List<Meals.Meal> meals);

    void onErrorLoading(String message);
}
