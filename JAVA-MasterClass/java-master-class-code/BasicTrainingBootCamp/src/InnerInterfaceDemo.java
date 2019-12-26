class Button {
    private String title;
    private OnclickListen onclickListen;
    //inner interfaces
    /*An interface which is declared inside another interface or class is called nested interface. They are also known as inner interface.
    Since nested interface cannot be accessed directly, the main purpose of using them is to resolve the namespace by grouping
    related interfaces (or related interface and class) together.  This way, we can only call the nested interface by using outer class or outer interface name followed by dot( . ), followed by the interface name.*/
    public interface OnclickListen
    {
        public void onClick() ;
    }//

    public Button(String title) {
        this.title = title;
        System.out.println(title + " Button got created");
    }

    //Getter & Setters
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public OnclickListen getOnclickListen() {return onclickListen;}
    public void setOnclickListen(OnclickListen onclickListen) {
        this.onclickListen = onclickListen;
    }
    public void submit()
    {
        getOnclickListen().onClick();
    }

}


public class InnerInterfaceDemo {

    public static void main(String[] args) {
        Button button=new Button("Submit");
        button.setOnclickListen(new Button.OnclickListen() {
            @Override
            public void onClick() {
                System.out.println("Button was clicked");
            }
        });
        button.submit();

        Button printButton=new Button("Print");
        //anonymous class - there is NO name to these type of classes ,
        // there are define and used at the same and can be used ONLY once.
        printButton.setOnclickListen(new Button.OnclickListen() {
            @Override
            public void onClick() {
                System.out.println("Document got printed ");
            }
        });

        printButton.submit();
    }



}
