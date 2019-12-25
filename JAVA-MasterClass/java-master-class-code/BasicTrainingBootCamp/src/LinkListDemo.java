import java.util.Iterator;
import java.util.LinkedList;

public class LinkListDemo {

    public static void main(String[] args) {
        LinkedList<String> listOfCities=new LinkedList<String>();
        listOfCities.add("New York");
        listOfCities.add("Paris");
        listOfCities.add("London");
        listOfCities.add("Rome");

        // using Iterator
        Iterator<String> simpleIterator2=listOfCities.iterator();
        while(simpleIterator2.hasNext())
        {
            System.out.println(""+simpleIterator2.next());
        }




        /*The below one does not work, as its create new iterator object every-time and which will ALWAYS point to the first element.*/
        /*while(listOfCities.iterator().hasNext())
        {
            System.out.println(""+listOfCities.iterator().next());
        }
        */


    }
}
