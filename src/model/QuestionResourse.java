/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.DBConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mithila
 */
public class QuestionResourse extends UnicastRemoteObject implements Resourse{
    
    
    PreparedStatement ps;
    ResultSet rs;
    
    ArrayList<Question> questions;
    public QuestionResourse()throws RemoteException
    {
        questions = new ArrayList<>();
    }   
    @Override
    public ArrayList<Question> getQuestions()
    {
      
       
        String sql = "SELECT *FROM question ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Question ques = new Question();
                ques.setId(rs.getInt("id"));
                ques.setQuestion(rs.getString("question"));
                ques.setAns1(rs.getString("ans1"));
                ques.setAns2(rs.getString("ans2"));
                ques.setAns3(rs.getString("ans3"));
                ques.setCurrect_ans1(rs.getString("currect_ans"));
                questions.add(ques);
            }    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
             System.out.println(ex.getMessage());
        }
        
        return questions;
    }  
    
    @Override
    public int getQuestionsItems()
    {
        int items=0;
        String sql = "SELECT COUNT(id)FROM question ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
            {
                items = rs.getInt("COUNT(id)");
            }    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
             System.out.println(ex.getMessage());
        }
        
        return items;
    }        

    @Override
    public void setResults(String name, int result) throws RemoteException {
        String sql = "INSERT INTO result(name,result)values('user' , "+result+")";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","");
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Your results is recorded successfully...!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+"noo");
        } catch (ClassNotFoundException ex) {
             System.out.println(ex.getMessage()+"noo");
        }
    }

    
}
