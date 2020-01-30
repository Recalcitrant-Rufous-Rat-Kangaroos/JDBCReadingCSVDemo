package edu.wpi.derbydemo;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainDemo {

    public static void main(String[] args){


        JDBCutils db = new JDBCutils();


        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;


        try {
            conn = JDBCutils.getConnection();
            stmt = conn.createStatement();

            JDBCutils.dropTable("Nodes", stmt);
            JDBCutils.createNodeTable(stmt);

            ArrayList<String[]> al = db.readCsvFile(db.getNodecsvPath());

            String sql = "INSERT INTO Nodes  VALUES(?,?,?,?,?,?,?,?,?)";

            for (String[] array : al) {// This loop is used to iterate through the arraylist
                  pstmt = conn.prepareStatement(sql);
                for (int i=0; i<array.length;i++) {//This loop is used to iterate through the array inside the arraylist
                    if(array[i].chars().allMatch(Character::isDigit)){
                        pstmt.setInt(i+1,Integer.parseInt(array[i]));
                    }else{
                        pstmt.setString(i+1,array[i]);
                    }
                }
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutils.close(conn, stmt);
        }

    }

}
