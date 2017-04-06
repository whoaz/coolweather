package com.helloz.android.coolweather.util;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.helloz.android.coolweather.db.City;
import com.helloz.android.coolweather.db.County;
import com.helloz.android.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zhouk on 2017/4/5.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray cities = new JSONArray(response);
                for (int i = 0; i < cities.length(); i++) {
                    JSONObject cityObj = cities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getInt("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}
