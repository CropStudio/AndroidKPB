package com.example.app4g.Utils;

import com.google.gson.Gson;

/**
 * Created by hynra [github.com/hynra] on 21/03/2018.
 */

public class GsonHelper {

    public static Object parseGson(String content, Object obj){
        obj = obj.getClass();
        obj = new Gson().fromJson(content, (Class<Object>) obj);
        return obj;
    }

}
