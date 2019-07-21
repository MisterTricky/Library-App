import java.sql.*;


public class DB_Connect
{
    public static void Connect () throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false");
        Statement stmt = conn.createStatement();

    }

}
