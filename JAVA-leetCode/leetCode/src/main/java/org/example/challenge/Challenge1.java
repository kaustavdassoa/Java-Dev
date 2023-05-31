package org.example.challenge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 *
 */
public class Challange1 {


    static final Integer [] numList= {3,3};
    static final Integer [] numList2= {3,2,4};
    static final Integer [] numList3= {2,7,11,15};
    static final Integer [] numList4= {2,7,9,8,5,7,6,1,10,12};
    static final Integer target = 6;
    static final Integer target2 = 6;
    static final Integer target3 = 9;
    static final Integer target4 = 3;

    public static void main(String[] args) {



        Challange1 sol=new Challange1();
        System.out.println(sol.mySolution(numList4, target4).toString());
        System.out.println(sol.bookSolution(numList4, target4).toString());

    }

    public List<Integer> mySolution(Integer [] num,Integer target)
    {
      List<Integer> index=new ArrayList<Integer>();
        for (int i=0;i< num.length;i++)
        {
            for(int j=i;j< num.length;j++)
            {
                int sum=num[i]+num[j];
                if((i !=j )&& sum==target)
                {
                    index.add(i);
                    index.add(j);
                }
            }
        }

        return index;
    }


    public List<Integer> bookSolution(Integer[] numbers, Integer target)
    {

        List<Integer> indexes=new ArrayList<>();
        for(int i=0;i<numbers.length;i++)
        {
            int x= numbers[i];
            int y=target-x;
            if(Arrays.asList(numbers).contains(y))
            {
                int z= Arrays.stream(numbers).collect(Collectors.toList()).indexOf(y);
                indexes.add(i);
                indexes.add(z);

            }

        }
        return indexes.stream().distinct().collect(Collectors.toList());
    }
}
