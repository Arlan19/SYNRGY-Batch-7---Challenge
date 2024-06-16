package com.allacsta.latihan.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

    public Map sukses(Object obj){
        Map map = new HashMap<>();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "Success");
        return map;
    }

    public Map error(Object obj, Object code){
        Map map = new HashMap();
        map.put("status", code);
        map.put("message", obj);
        return map;
    }

    public boolean chekNull(Object obj){
        if(obj == null){
            return true;
        }
        return  false;
    }

    public Map<String, Object> success(Map<String, Object> data, int status) {
        data.put("status", status);
        return data;
    }

    public Map templateSukses(Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "200");
        return map;
    }

    public Map Error(Object obj){
        Map map = new HashMap();
        map.put("status", 404);
        map.put("message", obj);
        return map;
    }
    public Map templateEror(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "404");
        return map;
    }
    public Map notFound(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "404");
        return map;
    }

}
