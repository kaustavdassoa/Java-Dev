package com.eample;

public class Main {

    public static void main(String[] args) {
        SiimpleStringUtility util=new SiimpleStringUtility();
        StringBuilder sb = new StringBuilder();
        while(sb.length() > 10) {
            sb = util.addCharater(sb, 'a');
        }
        System.out.println("Final String :"+sb);
    }

}
