/* NOTE 
 * The Connect statement is assigned within a try block in each of the methods in this class.
 * The Result set is used in its pure form to print out the results of a Query.
 * The caught exceptions will simply print out the stack trace of the error.
 * The database is simple.
 * The idea of writing the application in this manner: 
 * 3 Separate classes and their purpose:
 * 
 * 1.SQL Methods, the main class will never access this class directly.Contains NO main method, only methods to be called by the Implementation Class
 * 
 * 2.Implementation, this class contains the Implementation code for each of the methods in the
 * SQL_Methods Class and works as a buffer between the MAIN and SQL_Methods class.
 * 
 * 3.Main Class. The main class utilizes the Implementation class. The idea is that the main class contains as little code as possible, Only calling
 * the methods in the Implementation class which utilizes the SQL_Methods class which contains all the actual SQL code.
 * 
 *  I'm unsure about the exceptions in java, and am afraid that my implementation of these might be redundant.
 * 
 * */
import java.util.*;



public class LibraryMS_Main extends Implementation
{

    public static void main(String[] args)
    {
        //Declare Variables:
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("...WELCOME TO LIBRARY MS...");
    	int choice = Implementation.menu();
       

          //This method returns an Integer and thus we can use it to decide what should happen based on its return value

        if (choice == 1)//Option1: Update Book Info
        {
        	Implementation.Update();
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");



        }
        else if(choice == 2)//Option2: Delete a book by its ID
        {
        	Implementation.Delete();
        	
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");

        }
        else if(choice == 3)//Option3: Search for a Specific book
        {
        	
        	Implementation.Search();
        	
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");
        	if(in.nextInt()==1)
        	{
        		Implementation.menu();
        	}
        	else if(in.nextInt()==0) 
        	{
        		System.exit(0);
        	} 
        	

        }
        else if(choice == 4)//Option4: Add new books from a text File
        {
        	Implementation.AddBookFromFile();
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");
        	
        	if(in.nextInt()==1)
        	{
        		Implementation.menu();
        	}
        	else if(in.nextInt()==0) 
        	{
        		System.exit(0);
        	} 

        }
        else if(choice == 5)//Option5: Add a book by inputting the data manually
        {
        	Implementation.AddBookByInput();
        	
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");
        	
        	if(in.nextInt()==1)
        	{
        		Implementation.menu();
        	}
        	else if(in.nextInt()==0) 
        	{
        		System.exit(0);
        	} 

        }
        else if(choice == 6)//Option6: Show all available books in the database
        {
        	
        	System.out.println("Type 1 to return to the menu. 0 to quit the program");
        	
        	if(in.nextInt()==1)
        	{
        		Implementation.menu();
        	}
        	else if(in.nextInt()==0) 
        	{
        		System.exit(0);
        	} 
        	
        }
        else if(choice == 0)//Option7: Exit the program
        {
            //EXIT THE PROGRAM
            System.exit(0);
        }

        	in.close();


    }


}
