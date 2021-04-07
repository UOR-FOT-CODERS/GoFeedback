/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.DBConnection;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mithila
 */
public class ModelHelper {

    Connection con = DBConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public int isUser(String user_name, String password) {
        int flag = 0;
        String sql = "SELECT *FROM user WHERE username='" + user_name + "' AND password='" + password + "' LIMIT 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("user_type") == 1) {
                    flag = 1;
                } else {
                    flag = 2;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return flag;
    }

    public String getUserName(String user_name, String password) {
        String flag = "";
        String sql = "SELECT *FROM user WHERE username='" + user_name + "' AND password='" + password + "' AND user_type=2 LIMIT 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("user_type") == 2) {
                    flag = rs.getString("name");
                } else {
                    flag = "pp";
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return flag;
    }

    public void addQuiz(String question, String ans1, String ans2, String ans3, String cur_ans) {
        String sql = "INSERT INTO question(question,ans1,ans2,ans3,currect_ans) VALUES('" + question + "','" + ans1 + "','" + ans2 + "','" + ans3 + "','" + cur_ans + "')";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Question added Successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getQuestionNumbers() {
        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT *FROM question";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Question getQuestion(int id) {
        Question ques = new Question();
        String sql = "SELECT *FROM question WHERE id=" + id + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                ques.setId(rs.getInt("id"));
                ques.setQuestion(rs.getString("question"));
                ques.setAns1(rs.getString("ans1"));
                ques.setAns2(rs.getString("ans2"));
                ques.setAns3(rs.getString("ans3"));
                ques.setCurrect_ans1(rs.getString("currect_ans"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ques;
    }

    public void updateQuiz(String question, String ans1, String ans2, String ans3, String cur_ans, String id) {
        String sql = "UPDATE question SET question='" + question + "',ans1='" + ans1 + "',ans2='" + ans2 + "',ans3='" + ans3 + "',currect_ans='" + cur_ans + "' WHERE id=" + id + "";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Data Updated Successfully!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteQuiz(String id) {
        String sql = "DELETE FROM question WHERE id=" + id + "";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Data deleted Successfully!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        String sql = "SELECT *FROM question ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
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
        }

        return questions;
    }
    
    public void setHResults(String name, int result)  {
        String sql = "INSERT INTO result(name,result)values('"+name+"' , "+result+")";
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
