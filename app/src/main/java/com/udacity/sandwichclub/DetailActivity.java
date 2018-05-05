package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.ListUtils;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding mBinding;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json, this);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Populates UI with all required fields and data
     */
    private void populateUI(Sandwich sandwich) {
        // download image and attached to the image view
        Picasso.with(this).load(sandwich.getImage()).noPlaceholder()
                .error(R.drawable.unable_to_load_pic)
                .into(mBinding.imageIv);
        // Set description text
        mBinding.descriptionTv.setText(sandwich.getDescription());
        String alsoKnownAs = ListUtils.listToString(sandwich.getAlsoKnownAs(), this);
        // Set also known as text
        mBinding.alsoKnownTv.setText(alsoKnownAs);
        // Set place of origin text
        mBinding.originTv.setText(sandwich.getPlaceOfOrigin());
        String ingredients = ListUtils.listToString(sandwich.getIngredients(), this);
        // Set ingredients text
        mBinding.ingredientsTv.setText(ingredients);
    }
}
