package edu.wpi.utils;

import com.csvreader.CsvReader;

import javax.swing.plaf.nimbus.State;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCutils {


    private static String url;
    private static String driver;
    private static String nodecsvPath;



    static {
        try {

            Properties pro = new Properties();
            ClassLoader classLoader = JDBCutils.class.getClassLoader();
            URL res = classLoader.getResource("JDBCderby.properties");
            pro.load(new FileReader(res.getPath()));
            url = pro.getProperty("url");
            driver = pro.getProperty("driver");
            nodecsvPath = pro.getProperty("nodecsvPath");
            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNodecsvPath() {
        return nodecsvPath;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static Statement createStatement() throws SQLException {
        return JDBCutils.getConnection().createStatement();
    }

    public static void selectNode(Statement stmt, ResultSet rs) throws SQLException {

        String sql2  = "SELECT * FROM Nodes";
        rs = stmt.executeQuery(sql2);

        while(rs.next()){
            String nodeId = rs.getString("nodeId");
            System.out.println(nodeId);
        }
    }

    public static void insertNode(Connection conn, PreparedStatement pstmt) throws SQLException {
        ArrayList<String[]> al = JDBCutils.readCsvFile(JDBCutils.getNodecsvPath());


        String sql = "INSERT INTO Nodes VALUES(?,?,?,?,?,?,?,?,?)";

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
    }

    public static ArrayList<String[]> readCsvFile(String filePath) {
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            reader.readHeaders(); //Jump over the file header

            while (reader.readRecord()) {
                csvList.add(reader.getValues()); //Read in line by line and add to the arrayList
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvList;
    }

    /**
     * Prototype for dropping a table
     * @param tableName
     * @throws SQLException
     */
    public static void dropTable(String tableName, Statement stmt) throws SQLException{
        String sql = "drop table "+tableName;
        stmt.executeUpdate(sql);
    }

    public static void createNodeTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE Nodes(\n" +
                "    nodeId CHAR(10),\n" +
                "    xcoord INT, \n" +
                "    ycoord INT, \n" +
                "    floor INT, \n" +
                "    building VARCHAR(20),\n" +
                "    nodeType CHAR(4), \n" +
                "    longName VARCHAR(50), \n" +
                "    shortName VARCHAR(20), \n" +
                "    teamAssigned CHAR(6),\n" +
                "    PRIMARY KEY(nodeId)\n" +
                ")";

            stmt.executeUpdate(sql);

    }
    


    public static void close(Connection conn, Statement stmt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        close(conn, stmt);

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pstmt) {
        close(conn,stmt,rs);

        if(rs!=null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}