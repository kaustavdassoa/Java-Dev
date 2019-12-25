import java.util.ArrayList;

// Autoboxing Challenge - 1
// You job is to create a simple banking application.
// There should be a Bank class
// It should have an arraylist of Branches
// Each Branch should have an arraylist of Customers
// The Customer class should have an arraylist of Doubles (transactions)
// Customer:
// Name, and the ArrayList of doubles.
// Branch:
// Need to be able to add a new customer and initial transaction amount.
// Also needs to add additional transactions for that customer/branch
// Bank:
// Add a new branch
// Add a customer to that branch with initial transaction
// Add a transaction for an existing customer for that branch
// Show a list of customers for a particular branch and optionally a list
// of their transactions
// Demonstration autoboxing and unboxing in your code
// Hint: Transactions
// Add data validation.
// e.g. check if exists, or does not exist, etc.
// Think about where you are adding the code to perform certain actions



class Bank {
    private ArrayList<Customer> Branches;
}

class Branch{
    private ArrayList<Customer> listOfCustomers;
    private String branchName;

    public Branch(String branchName) {
        this.branchName=branchName;
        this.listOfCustomers = new ArrayList<Customer>();
    }
    public void listAllCustomers()
    {
        for (int i=0; i < this.listOfCustomers.size(); i++)
        {
            System.out.println("CustomerName :"+this.listOfCustomers.get(i).getCustomerName()+"--> Total Balance :"+this.listOfCustomers.get(i).getTransactions());
        }
    }

    public boolean addNewTransaction(String customerName, double ammount)
    {
        Customer customer=getCustomerByName(customerName);
        if(customer != null)
        {
            return customer.addNewTransaction(ammount);
        }
        return false;
    }

    public boolean addNewCustomer(String customerName, double deposit)
    {
        if(getCustomerByName(customerName) == null)
        {
            this.listOfCustomers.add(new Customer(customerName,deposit));
            return true;
        }
        else
        {
            return false;
        }
    }

    private Customer getCustomerByName(String customerName)
    {
        for(int i=0; i < this.listOfCustomers.size() ; i++)
        {
            if(this.listOfCustomers.get(i).getCustomerName().equals(customerName))
            {
                return this.listOfCustomers.get(i);
            }
          }
        return null;
    }



}//Branch

class Customer {
    private String customerName;
    private ArrayList<Double> transactions;


    public String getCustomerName() {
        return customerName;
    }

    public double getTransactions()
    {
        double totalTransactions=0.00;
        for(int i=0;i<this.transactions.size();i++)
        {
            totalTransactions += this.transactions.get(i);
        }
        return totalTransactions;
    }

    public boolean addNewTransaction(double amount)
    {
        this.transactions.add(amount);
        return true;
    }//addNewTransaction

    public Customer(String customerName, double anount) {
        this.customerName = customerName;
        this.transactions = new ArrayList<Double>();
        this.transactions.add(anount);
    }
}//Customer





public class AutoBoxingChallange {
    public static void main(String[] args) {


        Branch _001=new Branch("New York");
        _001.addNewCustomer("bob",200.00);
        _001.addNewCustomer("Jack",500.00);
        _001.addNewCustomer("Ryan",20.00);
        _001.addNewTransaction("Ryan",500.00);

        _001.listAllCustomers();
    }
}
