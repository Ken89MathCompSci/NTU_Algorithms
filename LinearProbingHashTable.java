import java.util.*;

// To read in sample data in csv format
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;

// Counter class to contain variables to compute the number of key comparisons when searching and hold index of item to be searched 
class Counter{
	public static int keycomparisons; // variable to hold number of key comparisons
	public static int index;		// variable to hold index of searched item
}

// Linear Probing Hashing table 
class LinearProbingHashTable
{
	// variables to store current size of Hash Table and maximum size of Hash Table
    private int currentSize, maxSize;
    
    // variables to store key-and-value pairs
    private String[] keys;
    private String[] vals;

    // constructor for Linear Probing Hash Table with total size given
    public LinearProbingHashTable(int capacity)
    {
        currentSize = 0;
        maxSize = capacity;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }

    // Clear the whole Linear Probing Hash Table
    public void makeEmpty()
    {
        currentSize = 0;	// Reset size of Linear Probing Hash Table to zero
        keys = new String[maxSize];		// Clear the array of keys
        vals = new String[maxSize];		// Clear the array of values
    }

    // function to return current size of Linear Probing Hash Table
    public int getSize()
    {
        return currentSize;
    }
    
    //
    public boolean contains(String key)
    {
        return get(key) !=  null;
    }

    // hash function using hashCode function to generate a hash value 
    private int hash(String key)
    {
        return key.hashCode() % maxSize;
    }

    // insert function to insert key and value into hash table
    public void insert(String key, String val)
    {
        int tmp = hash(key);
        
        // save the hash value for the current key in to the variable i
        int i = tmp;
        
        do
        {
        	// if the current slot with the index which is the hash value is empty...
            if (keys[i] == null)
            {	
                keys[i] = key;	// save current key into keys[i]
                vals[i] = val;	// save current value into vals[i]
                currentSize++;	// increment size of Linear Probing Hash Table by one
                return;
            }
            
            // if the current slot with the index already has the current key
            if (keys[i].equals(key))
            {	
            	// overwrite the current corresponding value with the new value val
                vals[i] = val;
                return;
            }
            
            // if collision occurs, rehash!
            i = (i + 1) % maxSize;
            
        } while (i != tmp);	// continue going through the do-while loop while the current index is not equal to original hashed value of the key
    }
    
    // retrieve value for current key
    public String get(String key)
    {
        int i = hash(key);
        
        // initialise the key comparison variable to 0!
        Counter.keycomparisons=0;
        
        while (keys[i] != null)
        {
        	Counter.keycomparisons++;
            if (keys[i].equals(key)) {
            	
                return vals[i];
            }
            
            i = (i + 1) % maxSize;//
            
            Counter.index = i;     
        }
       // System.out.println("Number of key comparisons = " + counter);*/
        return null;
    }

    public void remove(String key)
    {
        if (!contains(key))
            return;

        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % maxSize;//
        keys[i] = vals[i] = null;

        for (i = (i + 1) % maxSize; keys[i] != null; i = (i + 1) % maxSize)//
        {
            String tmp1 = keys[i], tmp2 = vals[i];
            keys[i] = vals[i] = null;
            currentSize--;
            insert(tmp1, tmp2);
        }
        currentSize--;
    }

    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++)
            if (keys[i] != null)
                System.out.println(keys[i] +" "+ vals[i]);
        System.out.println();
    }
}


public class Hashing_Linear_Probing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        LinearProbingHashTable lpht = new LinearProbingHashTable(input.nextInt());

        char secondoption;
        
        do
        {
            System.out.println("\nCustomer Data Operations\n");
            
            System.out.println("1. Insert Customer id and salary into Hash Table ");
            
            System.out.println("2. Remove Customer with id ");
            
            System.out.println("3. Get details of Customer id");
            
            System.out.println("4. Clear details of Customer id");
            
            System.out.println("5. Total number of customers");
            
            //System.out.println("6. Current value of counter: ");

            int option = input.nextInt();
            switch (option)
            {
                case 1 :
                
                
                	for (int i=0; i < 200000;i++) {
                		// generate from 100000 to 500000
                		lpht.insert(Integer.toString((int)(Math.random() * ((500000 - 0) + 1)) + 0), Integer.toString(10000));
                	}*/
                	
            	    lpht.printHashTable();
            	    
                    break;
                    
                case 2 :
                	
                    System.out.println("Enter key");
                    lpht.remove(input.next());
                    
                    break;
                    
                case 3 :
                    System.out.println("Enter Customer's banking account number: ");
                    
                    long startTime = System.nanoTime();
                    System.out.println("Customer's salary = "+ lpht.get(input.next()));                 
                    
                    System.out.println("Number of key comparisons = " + Counter.keycomparisons);
                    
                    System.out.println("Index = " + Counter.index);
                    
                    long estimatedTime = System.nanoTime() - startTime;
                    
                    System.out.println("Searching time duration : " + estimatedTime + " nanoseconds");
                    
                    break;
                    
                case 4 :
                	
                    lpht.makeEmpty();
                    
                    System.out.println("Customers Data Cleared\n");
                    
                    break;
                    
                case 5 :
                    System.out.println("Total number of Customers Data = "+ lpht.getSize() );
                    break;
                    
                case 6 :
                    System.out.println("Counter value = "+ Counter.keycomparisons );
                    break;
                    
                default :
                    System.out.println("Invalid Option \n ");
                    break;
            }        

            System.out.println("\nPress any key to continue, press \"n\" to quit\n");
            secondoption = input.next().charAt(0);
            
        } while ((secondoption != 'N') && (secondoption != 'n'));

	}

}
