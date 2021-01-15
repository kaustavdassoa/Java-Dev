package com.eample;

public class SiimpleStringUtility {

    private int charCount=0;


    public StringBuilder addCharater(StringBuilder stringBuilder,char charater)
    {


        stringBuilder.append(charater);
        charCount++;
        return stringBuilder;
    }


}
