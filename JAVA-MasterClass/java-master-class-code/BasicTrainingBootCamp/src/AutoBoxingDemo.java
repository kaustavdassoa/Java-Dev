import java.util.ArrayList;

public class AutoBoxingDemo {
    public static void main(String[] args) {
        int i=10;
        System.out.println("AutoBoxing Value "+Integer.valueOf(i));
        Integer intergerVal=new Integer(i); // explicitly autoboxing
        System.out.println("Unboxing Value "+intergerVal.intValue()); // explicitly unboxing


        Integer newIntergerValue=i; // JAVA automatically autoboxing
        System.out.println("Autoboxing "+newIntergerValue);
        i=newIntergerValue; // JAVA automatically unboxing
        System.out.println("Unboxing "+i);
    }
}



