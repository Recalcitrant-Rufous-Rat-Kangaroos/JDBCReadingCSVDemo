package edu.wpi.derbydemo;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainDemo {

    public static void main(String[] args){


        JDBCutils db = new JDBCutils();


        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JDBCutils.getConnection();
            stmt = conn.createStatement();
            JDBCutils.dropTable("Nodes", stmt);
            JDBCutils.createNodeTable(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutils.close(conn, stmt);
        }
        System.out.println("John");


        ArrayList<String[]> al = db.readCsvFile(db.getNodecsvPath());











    }

}
