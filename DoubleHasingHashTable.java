import java.util.*;


class Counter{
	public static int keycomparisons;
	public static int index;
}


/* Class LinkedHashEntry */
class HashEntry 
{
    String key;
    int value;    
 
    /* Constructor */
    HashEntry(String key, int value) 
    {
        this.key = key;
        this.value = value;        
    }
}

class DoubleHasingHashTable
{
    private int TABLE_SIZE;
    private int size;
    private HashEntry[] table;
    private int primeSize;

    public DoubleHasingHashTable(int ts)
    {
        size = 0;
        TABLE_SIZE = ts;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        primeSize = getPrime();
    }

    public int getPrime()
    {
        for (int i = TABLE_SIZE - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }
    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public int get(String key)
    {
    	Counter.keycomparisons=0;
    	
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );

        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
        	Counter.keycomparisons++;
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        if (table[hash1] == null) {
        	Counter.index = hash1; 
            return -1;
        }
        Counter.index = hash1; 
        return table[hash1].value;
    }

    public void insert(String key, int value)
    {
        if (size == TABLE_SIZE)
        {
            System.out.println("Table full");
            return;
        }
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
        while (table[hash1] != null)
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = new HashEntry(key, value);
        size++;
    }

    public void remove(String key)
    {
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = null;
        size--;
    }

    private int myhash1(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }

    private int myhash2(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return primeSize - hashVal % primeSize;
    }

    public void printHashTable()
    {
        System.out.println("\nHash Table");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key +" "+table[i].value);
    }
}


public class CZ2001_Example_Class_2_DoubleHashing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        /* Make object of HashTable */
        DoubleHasingHashTable dhht = new DoubleHasingHashTable(scan.nextInt());

        char ch;
        /*  Perform HashTable operations  */
        do
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. get");
            System.out.println("4. check empty");
            System.out.println("5. clear");
            System.out.println("6. size");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :

                    
                    for (int i=0; i < 12000;i++) {
                		// generate from 100000 to 500000
                		dhht.insert(Integer.toString((int)(Math.random() * ((500000 - 100000) + 1)) + 100000), 10000);
                	}
                    
                    dhht.printHashTable();
                    break;
                case 2 :
                    System.out.println("Enter key");
                    dhht.remove( scan.next() );
                    break;
                case 3 :
                    System.out.println("Enter key");
                    
                    long startTime = System.nanoTime();
                    
                    System.out.println("Value = "+ dhht.get( scan.next() ));
                    long estimatedTime = System.nanoTime() - startTime;
                    System.out.println("Searching time duration : " + estimatedTime);
                    
                    System.out.println("Number of key comparisons = " + Counter.keycomparisons);
                    System.out.println("Index = " + Counter.index);
                    

                    break;
                case 4 :
                    System.out.println("Empty Status " +dhht.isEmpty());
                    break;
                case 5 :
                    dhht.makeEmpty();
                    System.out.println("Hash Table Cleared\n");
                    break;
                case 6 :
                    System.out.println("Size = "+ dhht.getSize() );
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }



            System.out.println("\nPress any key to continue, press \"n\" to quit\n");
            ch = scan.next().charAt(0);
        } while (ch != 'N'&& ch != 'n');


	}

}
