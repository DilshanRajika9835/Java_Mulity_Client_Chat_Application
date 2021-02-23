package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   private  static DBConnection dbConnection;
   private Connection connection;
   private DBConnection(){
       try {
           Class.forName("com.mysql.jdbc.Driver");
          connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ChatApplication","root","ijse");
       } catch (SQLException | ClassNotFoundException throwables) {
           throwables.printStackTrace();
       }
   }

   public  Connection getConnection(){
       return connection;
   }
   public  static  DBConnection getInstance(){
       if(dbConnection==null){
           dbConnection=new DBConnection();
       }
       return dbConnection;
   }
}
