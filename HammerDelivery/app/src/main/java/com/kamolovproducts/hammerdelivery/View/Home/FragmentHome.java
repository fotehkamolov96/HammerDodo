package com.kamolovproducts.hammerdelivery.View.Home;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kamolovproducts.hammerdelivery.R;
import com.kamolovproducts.hammerdelivery.Utils;
import com.kamolovproducts.hammerdelivery.adapter.RecyclerViewHomeAdapter;
import com.kamolovproducts.hammerdelivery.adapter.ViewPagerHeaderAdapter;
import com.kamolovproducts.hammerdelivery.category.CategoryActivity;
import com.kamolovproducts.hammerdelivery.Home.HomePresenter;
import com.kamolovproducts.hammerdelivery.Home.HomeView;
import com.kamolovproducts.hammerdelivery.Model.Categories;
import com.kamolovproducts.hammerdelivery.Model.Meals;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHome extends Fragment implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private BottomSheetBehavior bottomSheetBehavior;

    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;

    HomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(getActivity());

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
        recyclerViewCategory = view.findViewById(R.id.recyclerCategory);
        showLoading();

//        ImageButton image = view.findViewById(R.id.custom_menu);
//        image.setOnClickListener((View.OnClickListener) view17 -> {
//            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog((getActivity()), R.style.BottomSheetDialogTheme);
//            View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.z_bottom_sheet, (LinearLayout) view17.findViewById(R.id.bottomSheetContainer));
//
//            bottomSheetView.findViewById(R.id.carduser1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Uri uri = Uri.parse("http://instagram.com/_u/kamolovme");
//                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
//                    i.setPackage("com.instagram.android");
//
//                    try {
//                        startActivity(i);
//                    } catch (ActivityNotFoundException e) {
//                        startActivity(new Intent(Intent.ACTION_VIEW,
//                                Uri.parse("http://instagram.com/kamolovme")));
//                    }
//                }
//            });
//
//            bottomSheetView.findViewById(R.id.carduser4).setOnClickListener(v -> shareImageWithText());
//
//            bottomSheetView.findViewById(R.id.carduser2).setOnClickListener(v -> sendEmail());
//
//            bottomSheetView.findViewById(R.id.carduser3).setOnClickListener(v -> {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.namoz"));
//                startActivity(browserIntent);
//            });
//
//            bottomSheetDialog.setContentView(bottomSheetView);
//            bottomSheetDialog.show();
//        });


        return view;
    }

    @Override
    public void showLoading() {
        //findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        //findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        //findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        //findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, getActivity());

        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            //make an intent to DetailActivity (get the name of the meal from the edit text view, then send the name of the meal to DetailActivity)
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, getActivity());
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });

    }


    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Title", message);
    }

}