/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mithila
 */
public class DBConnection {
    
    public static Connection getConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;

    }        
}
