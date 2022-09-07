package com.example.enummap;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumMapTest {

    enum Keys{
        ONE,
        FOUR,
        THREE,
        TWO;
    }

    public static void main(String[] args) {
        EnumMap<Keys,String> enumMap=new EnumMap<Keys, String>(Keys.class);
        enumMap.put(Keys.ONE,"one");
        enumMap.put(Keys.TWO,"two");
        enumMap.put(Keys.THREE,"three");
        enumMap.put(Keys.FOUR,"four");

        System.out.println(enumMap.toString());
    }
}
