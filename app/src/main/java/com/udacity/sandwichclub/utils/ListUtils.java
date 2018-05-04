package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     * Converts given list of strings to one string separated with comma delimited
     * @param context
     * @param list
     * @return
     */
    public static String listToString(List<String> list, Context context) {
        String result = "";
        if(list.size() == 0) {
            // default string
            return context.getResources().getString(R.string.not_available);
        }
        for(int i=0; i<list.size(); i++) {
            result = result.concat(list.get(i));
            if(i < list.size() - 1) {
                result = result.concat(", ");
            }
        }
        return result;
    }

    /**
     * Converts JSON array to list of strings
     *
     * @param jsonObject
     * @param arrayKey
     * @return
     * @throws JSONException
     */
    public static List<String> JSONArrayToList(JSONObject jsonObject, String arrayKey) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray(arrayKey);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i<jsonArray.length(); i++) {
            list.add((String) jsonArray.get(i));
        }
        return list;
    }
}
