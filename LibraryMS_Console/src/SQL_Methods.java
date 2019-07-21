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
import java.sql.*;


public class SQL_Methods
{

    public static void Select_Books_Available() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx");
             Statement stmt = conn.createStatement()) {
            String sqlSelect = "SELECT * FROM books WHERE qty > 0";

        ResultSet rset = stmt.executeQuery(sqlSelect);
        while (rset.next()) {
            System.out.println(rset.getInt("id") + ", "
                    + rset.getString("author") + ", "
                    + rset.getString("title") + ", "
                    + rset.getInt("qty"));
            
    		conn.close();
    		stmt.close();
        }//END WHILE
    }//End Try Block Start Catch 
      catch (SQLException e)
        {
        System.out.println("Something went wrong");


        }




    }//End Method


    public static void Select_Books_By_Title(String Title) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx");Statement stmt = conn.createStatement())
        {
        	String sqlSelect_By_Title ="SELECT title, author, qty FROM books WHERE title LIKE '"+Title+"'" ;
        	
        	ResultSet rset = stmt.executeQuery(sqlSelect_By_Title);
        	
        	while(rset.next()) 
        	{
        		System.out.println("...These are the matching results...:\n");
        		//Print Result Of Query
        		System.out.println(rset.getString("title") + ","
        							+ rset.getString("author")+","
        							+ rset.getInt("qty"));
        		
        		conn.close();
        		stmt.close();
        	}

        }//End Try Block Start Catch
        catch(SQLException e)
         {
             e.printStackTrace();
             

         }
        

    }//End Method

    public static void Select_Books_By_Author(String author)throws SQLException  {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx"); Statement stmt = conn.createStatement())
        {
        	String sqlSelect_By_Author ="SELECT title, author, qty FROM books WHERE title LIKE '"+Author+"'" ;
        	
        	ResultSet rset = stmt.executeQuery(sqlSelect_By_Author);
        	
        	while(rset.next()) 
        	{
        		System.out.println("...These are the matching results...:\n");
        		//Print Result Of Query
        		System.out.println(rset.getString("title") + ","
        							+ rset.getString("author")+","
        							+ rset.getInt("qty"));
        		
        		conn.close();
        		stmt.close();

        }
        	}
        catch(SQLException e)
        {
            e.printStackTrace();

        }//End Catch Block

        } //End Method

    //Add a book

    public static void Add_Book(int BookID, String Title, String Author, int Qty_Available) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx");
             Statement stmt = conn.createStatement())
        {
        	String sql_Add_Book ="INSERT INTO ebookstore_db(id,title,author,qty) VALUES(BookID, Title, Author, Qty_Available);";
        	int count_Inserted = stmt.executeUpdate(sql_Add_Book); //Count the amount of records inserted, In this case it will always be just one.
        	
        	System.out.println("Amount of Records Inserted: " + Integer.toString(count_Inserted));
        	        	
    		conn.close();
    		stmt.close();

        }//End Try Block Start Catch
        catch(SQLException e)
        {
            e.printStackTrace();

        }

    }//End Method

    //Update Book Information
    public static void Update_Book_Info(int BookIDToBeUpdated,String Title, String Author, int Qty_Available) throws SQLException
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx");
             Statement stmt = conn.createStatement())
        {
        	String sqlUpdate_Info = "UPDATE books WHERE id = '"+BookIDToBeUpdated+"' SET title ='"+Title+"', Author = '"+Author+"', qty = '"+Qty_Available+"'";
        	int Count_Updated = stmt.executeUpdate(sqlUpdate_Info);
        	
        	System.out.println(Integer.toString(Count_Updated) + " "+ "Rows updated.");
        	
        }//End Try Block Start Catch
        
       	
        catch(SQLException e)
        {
            e.printStackTrace();

        }

    }//End Method

    //Delete a specific book

    public static void Delete_A_Book(String Title, String Author) throws SQLException
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false", "myuser", "xxxx");
             Statement stmt = conn.createStatement())
        {
        	String sqlDelete_Book = "DELETE * FROM books WHERE title = Title AND author = Author";
        	int Count_Deleted = stmt.executeUpdate(sqlDelete_Book);
        	
        	System.out.println(Integer.toString(Count_Deleted) + " "+ "Rows deleted.");
        	

        }//End Try Block Start Catch
        catch(SQLException e)
        {
            e.printStackTrace();

        }

    }//End Method



}//END OF CLASS SQL_Methods

