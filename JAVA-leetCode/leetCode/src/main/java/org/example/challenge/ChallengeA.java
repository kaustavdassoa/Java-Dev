package org.example.challenge;

import java.util.ArrayList;
import java.util.List;

class DataType<E>{
    long total;
    List<E> listOfElemets;

    public DataType() {
        this.total = 0;
        this.listOfElemets = new ArrayList();
    }

    void add(E newElement)
    {
        listOfElemets.add(newElement);
        total=total+(Integer)newElement;
    }

    List<E> getListOfElemets()
    {
        return listOfElemets;
    }

    boolean find(E element)
    {
        return listOfElemets.contains(element);
    }
}


public class Challenge3 {

    public static void main(String[] args) {
        DataType<Integer> dataStore=new DataType<Integer>();
        dataStore.add(7);
        dataStore.add(8);
        System.out.println("Find 8 ->"+dataStore.find(8));
        System.out.println("Find 2 ->"+dataStore.find(2));
        System.out.println("All Elements "+dataStore.listOfElemets.toString());

    }


}
