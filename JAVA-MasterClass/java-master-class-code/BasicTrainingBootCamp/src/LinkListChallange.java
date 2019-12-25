import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 Create a new Link list function for maintain a list of cities - with function like addCity() removeCity() printCities()
 Leverage iterators and there relative positions.
 https://www.geeksforgeeks.org/merge-sort-for-linked-list/
*/

public class LinkListChallange {

    public  LinkedList<String> listOfCities;

    public LinkListChallange() {
        this.listOfCities=new LinkedList<String>();
        listOfCities.add("New York");
        listOfCities.add("Paris");
        listOfCities.add("London");
        listOfCities.add("Rome");
    }

    public void addCity(String newCity)
    {
        //TO-DO : Add city in ascending order
        if(!findCity(newCity))
             this.listOfCities.add(newCity);

    }
    public void removeCity(String newCity)
    {
        ListIterator <String> listIterator=this.listOfCities.listIterator();
        while(listIterator.hasNext() && !this.listOfCities.isEmpty())
        {
            String currentCityName=listIterator.next();
            if(currentCityName.equals(newCity))
            {
                listIterator.remove();
            }
        }

    }

    public boolean findCity(String newCity)
    {
        ListIterator <String> listIterator=this.listOfCities.listIterator();
        while(listIterator.hasNext() && !this.listOfCities.isEmpty())
        {
            String currentCityName=listIterator.next();
            if(currentCityName.equals(newCity))
            {
                return true;
            }
        }
        return false;
    }

    private void printCities()
    {
        Iterator iterator=this.listOfCities.iterator();
        while(iterator.hasNext())
        {
            System.out.println(""+iterator.next());
        }
    }

    public static void main(String[] args)
    {

        LinkListChallange LinkListChallange=new LinkListChallange();
        LinkListChallange.printCities();
        LinkListChallange.addCity("New Delhi");
        LinkListChallange.addCity("New Delhi");
        System.out.println("\n\nAfter adding new city\n\n");
        LinkListChallange.printCities();
        LinkListChallange.removeCity("Paris");
        System.out.println("\n\nAfter removing new city\n\n");
        LinkListChallange.printCities();
    }


}
