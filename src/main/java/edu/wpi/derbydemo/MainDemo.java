package edu.wpi.derbydemo;

import edu.wpi.utils.JDBCutils;


import java.sql.*;
import java.util.ArrayList;

public class MainDemo {

    public static void main(String[] args){


        JDBCutils db = new JDBCutils();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;


        try {
            conn = JDBCutils.getConnection();
            stmt = conn.createStatement();


            JDBCutils.dropTable("Nodes", stmt);
            JDBCutils.createNodeTable(stmt);

            ArrayList<String[]> al = db.readCsvFile(db.getNodecsvPath());

            String sql = "INSERT INTO Nodes  VALUES(?,?,?,?,?,?,?,?,?)";

            for (String[] array : al) {
                  pstmt = conn.prepareStatement(sql);
                for (int i=0; i<array.length;i++) {
                    if(array[i].chars().allMatch(Character::isDigit)){
                        pstmt.setInt(i+1,Integer.parseInt(array[i]));
                    }else{
                        pstmt.setString(i+1,array[i]);
                    }
                }
                pstmt.executeUpdate();
            }

            String sql2  = "SELECT * FROM Nodes";
            rs = stmt.executeQuery(sql2);

            while(rs.next()){
                String nodeId = rs.getString("nodeId");
                System.out.println(nodeId);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutils.close(conn,stmt,rs,pstmt);
        }




    }
}
