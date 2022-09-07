package com.example.enummap;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String > hashMap=new HashMap<>();
        hashMap.put("Key1","value1");
        hashMap.put(null,"value2");
        hashMap.put("Key1","value3"); // duplicate key not allowed - override the value of existing key will new value
        hashMap.put(null,"value4"); // same applicable with null too
        System.out.println("Hash-Map :"+hashMap.toString());
    }
}
