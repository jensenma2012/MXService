package com.xiaoma.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonUtil {

    @SuppressWarnings("unchecked")
    public static List<String> convertStringToList(String string) {
        Gson gson = new Gson();
        List<String> list = gson.fromJson(string, List.class);
        return list;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertStringToMap(String string) {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(string, Map.class);
        return map;
    }

    public static String convertListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        Gson gson = new Gson();
        String string = gson.toJson(list);
        return string;
    }

}
