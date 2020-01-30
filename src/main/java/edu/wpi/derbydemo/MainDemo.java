package edu.wpi.derbydemo;

import edu.wpi.utils.JDBCutils;


import java.sql.*;
import java.util.ArrayList;

public class MainDemo {

    public static void main(String[] args){


      //  JDBCutils db = new JDBCutils();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;


        try {
            conn = JDBCutils.getConnection();
            stmt = conn.createStatement();


            JDBCutils.dropTable("Nodes", stmt);
            JDBCutils.createNodeTable(stmt);


            JDBCutils.insertNode(conn, pstmt);

            JDBCutils.selectNode(stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutils.close(conn,stmt,rs,pstmt);
        }




    }
}
