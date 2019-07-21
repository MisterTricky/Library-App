
/* NOTE 
 * The Connect statement is assigned within a try block in each of the methods in the SQL_Methods class.
 * The Result set is used in its pure form to print out the results of a Query.
 * The caught exceptions will simply print out the stack trace of the error.
 * The database is simple.
 * The idea of writing the application in this manner:
 * Easy Upgradeability
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

import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;

public class Implementation extends SQL_Methods {

	public static int menu() { // Declare Scanner Object
		Scanner input = new Scanner(System.in);
		System.out.println("Menu:\n" + "1.Update Book Information.\n" 
							+ "2.Delete a Book.\n"
							+ "3.Search For A Specific Book.\n" 
							+ "4.Add New Books from a File.\n"
							+ "5.Add A Single Book by Manually Inputting the Data.\n" 
							+ "6.Show All Available Books.\n"
							+ "0.Quit.");
		int value = input.nextInt();
		input.close();

		return value;

	}

	public static void Update() {
		// Declare Scanner object for user input
		Scanner in = new Scanner(System.in);
		//Get the value for each field from user input
		System.out.println(
				"\nEnter the BookID of the book you want to Update. If you know the Title or Author you can use the Search Function to find the ID. :):\t");
		//int old_id = in.nextInt();
		try {
			int old_id= System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("\nEnter the NEW title or the old one if the title has not changed.:\t");
		String title = in.nextLine();
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("Enter the NEW author name or the old name if the author did not change it. :)");
		String author = in.nextLine();
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("Enter the changed QTY or the same QTY if the QTY did not change:");
		int qty_available = in.nextInt();

		try {
			SQL_Methods.Update_Book_Info(old_id, title, author, qty_available);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		in.close();

	}

	public static void Delete() {
		// Declare holder variables

		String title = null;
		String author = null;

		// Create Scanner Object

		Scanner in = new Scanner(System.in);
		System.out.println("\nPlease Enter the Details of the book you'd like to delete:\n");
		System.out.println("Enter the Author:\n");

		author = in.nextLine();
		// Prevents system console from displaying the two output lines directly after
		// each other
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("Enter the Title:\n ");

		title = in.nextLine();

		try {
			SQL_Methods.Delete_A_Book(title, author);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		in.close();

	}

	public static void Search() {
		// Declare holder variables
		Scanner in = new Scanner(System.in);
		String title;
		String author;

		System.out.println("\nWould you like to search by Author(1) or Title(2)? (Enter the numerical value):\n");

		// Case or Switch could be used here but I prefer IF statements for longer
		// sections of code.
		// If Statement to check user choice and do the appropriate action

		if (in.nextInt() == 1)// Searches by author only
		{
			System.out.println("Enter the Author:");
			author = in.nextLine();

			try {
				SQL_Methods.Select_Books_By_Author(author);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (in.nextInt() == 2) {
			System.out.println("Enter the Title:");
			title = in.nextLine();

			try {
				SQL_Methods.Select_Books_By_Title(title);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		in.close();
	}

	public static void AddBookFromFile()
	{

		// I will be using a Scanner to get the text from the text file
		try {
			//Assigns a Scanner object to the text file
			Scanner InputFile = new Scanner(new File("newbooks.txt"));
			//Iterate through each Line of the File
			while(InputFile.hasNext())
			{
				//Split the String by the "," Delimiter
				String[] bookdata = InputFile.nextLine().split(",");
				//Assign variables from the array
				int BookID 	  	  = Integer.parseInt(bookdata[0]);
				String Title  	  = bookdata[1];
				String Author 	  = bookdata[2];
				int Qty_Available = Integer.parseInt(bookdata[3]);

				//Clear the array of values for reuse
				bookdata = null;

				try {
					SQL_Methods.Add_Book(BookID,Title,Author,Qty_Available);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				
				InputFile.close();

			}
			
			
			

		} //End Try
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		

	}

	public static void AddBookByInput() {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the BookID:\t");
		int BookID = in.nextInt();
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		System.out.println("\nEnter the Title of the Book:\t");
		String Title = in.nextLine();
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		System.out.println("Enter the Name of the Author:\t");
		String Author = in.nextLine();
		try {
			System.in.read();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		System.out.println("Enter the Quantity of books that will be available:\t");
		int Qty_Available = in.nextInt();

		try {
			SQL_Methods.Add_Book(BookID, Title, Author, Qty_Available);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		in.close();

	}

}
