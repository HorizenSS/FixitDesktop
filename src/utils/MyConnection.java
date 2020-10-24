/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection myCon;
    private String url="jdbc:mysql://127.0.0.1:3306/pidev"; 
    private String usr="root";
     private String pwd="";
     Connection con;
    public MyConnection(){
    try {
      con= (Connection) DriverManager.getConnection(url, usr, pwd);
    }
    catch(SQLException e ){
    }
   
    }
     public static MyConnection getInstance(){
        if(myCon==null)
        {
          myCon=new MyConnection();
        }
        return myCon;
    }

    public Connection getConnection() {
        return con;
    }

    public PreparedStatement PrepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
