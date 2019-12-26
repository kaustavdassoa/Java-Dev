/*
An abstract class is a class that is declared abstract — it may or may not include abstract methods.
*/
abstract class AbstractClass
{
 private String attribute;
 public abstract void ToBeDefineMethod();
 public void inherededMethod(){
     System.out.println("This method is inherited from the parent Class ");
 }
}

class ImplementingClass extends AbstractClass{

    @Override
    public void ToBeDefineMethod() {
        System.out.println("This method is defined in ImplementingClass");
    }

}

public class AbstractClassDemo {

    public static void main(String[] args) {
        ImplementingClass implementingClassObj=new ImplementingClass();
        implementingClassObj.ToBeDefineMethod();
        implementingClassObj.inherededMethod();

    }
}
