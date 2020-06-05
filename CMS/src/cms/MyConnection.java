package cms;
import java.sql.*;


public class MyConnection {
    
    public static Connection getConnection(){
        Connection con=null;
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UMS","root","");
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (Exception ex) {
            System.out.println("No connection");
        }
        return con;
        
    }
    
}
