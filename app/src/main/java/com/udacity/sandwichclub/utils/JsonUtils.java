package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static class SandwichJSONContract {
        public static final String NAME = "name";
        public static final String MAIN_NAME = "mainName";
        public static final String ALSO_KNOWN_AS = "alsoKnownAs";
        public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
        public static final String INGREDIENTS = "ingredients";
    }

    public static Sandwich parseSandwichJson(String json, Context context) {
        Sandwich sandwich = new Sandwich();
        try {
            // Parse json object from string
            JSONObject sandwichJSON = new JSONObject(json);
            // Get name object
            JSONObject name = sandwichJSON.getJSONObject(SandwichJSONContract.NAME);
            // Get main name string
            sandwich.setMainName(name.getString(SandwichJSONContract.MAIN_NAME));
            // Get also known as array
            sandwich.setAlsoKnownAs(ListUtils.JSONArrayToList(name, SandwichJSONContract.ALSO_KNOWN_AS));
            // Get place of origin string
            String placeOfOrigin = sandwichJSON.getString(SandwichJSONContract.PLACE_OF_ORIGIN);
            if(placeOfOrigin.equals("")) {
                sandwich.setPlaceOfOrigin(context.getResources().getString(R.string.not_available));
            } else {
                sandwich.setPlaceOfOrigin(sandwichJSON.getString(SandwichJSONContract.PLACE_OF_ORIGIN));
            }
            // Get description string
            sandwich.setDescription(sandwichJSON.getString(SandwichJSONContract.DESCRIPTION));
            // Get image url
            sandwich.setImage(sandwichJSON.getString(SandwichJSONContract.IMAGE));
            // Get ingredients array
            sandwich.setIngredients(ListUtils.JSONArrayToList(sandwichJSON, SandwichJSONContract.INGREDIENTS));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
