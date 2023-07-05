package com.twoitesting.utils;

import java.util.HashMap;
import java.util.Map;

public class SharedDictionary {
    //Shared dictionary class used primarily for multithreading in conjunction with pico-container injection

    //Define dictionary data type
    private final Map<String, Object> sharedMap = new HashMap<String, Object>();

    //Add to dictionary key and object, object in this case are web drivers
    public void addDict(String key, Object value) {
        sharedMap.put(key, value);
    }

//    //Read from dictionary to get key, in this case webdriver
//    public Object readDict(String key) {
//        return sharedMap.get(key);
//    }
//
//    //Check if dictionary contains key
//    public boolean containsKey(String key) {
//        return sharedMap.containsKey(key);
//    }
}