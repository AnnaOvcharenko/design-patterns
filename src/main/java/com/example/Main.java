package com.example;

import java.util.HashMap;

/**
 * @author Anna Ovcharenko
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(3);
        map.put(1, "one");
        for(var key:map.keySet()){
            System.out.println(key);
        }
    }

}


