/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Sujan
 */
public class Chart extends JFrame {

    private static final long serialVersionUID = 1L;
    PreparedStatement ps;
    ResultSet rs;

    public Chart(String appTitle) {
        super(appTitle);

        // Create Dataset  
        CategoryDataset dataset = createDataset();

        //Create chart  
        JFreeChart chart = ChartFactory.createBarChart(
                "Quiz Result Statistic", //Chart Title  
                "Participants", // Category axis  
                "Result Percentage", // Value axis  
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public Chart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String sql = "SELECT *FROM result ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int result = rs.getInt("result");
                dataset.addValue(result, name, "");

            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return dataset;
    }

}
