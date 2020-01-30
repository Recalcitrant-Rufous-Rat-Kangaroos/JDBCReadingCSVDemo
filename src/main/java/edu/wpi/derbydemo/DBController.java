package edu.wpi.derbydemo;

import com.csvreader.CsvReader;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;

public class DBController {
    private static Connection connection = null;

    public DBController(){}

    /**
     * Prototype for creating a table.
     * @param tableName
     * @param fields
     * @throws SQLException
     */
    public void createTable(String tableName, String fields) throws SQLException{
        Statement stmt = connection.createStatement();
        String str = "create table "+tableName+" ( "+fields+" )";
        int count = stmt.executeUpdate(str);
    }

    /**
     * Prototype for inserting values into a table
     * @param tableName
     * @param fields
     * @throws SQLException
     */
    public void insert(String tableName, String fields)throws SQLException{
        Statement stmt = connection.createStatement();
        String str = "insert into "+tableName+" values("+fields+")";
        stmt.executeUpdate(str);
    }

    /**
     * Prototype for dropping a table
     * @param tableName
     * @throws SQLException
     */
    public void dropTable(String tableName) throws SQLException{
        Statement stmt = connection.createStatement();
        String str = "drop table "+tableName;
        stmt.execute(str);
    }

    /**
     * Selects the table with a given condition. The condition can be left empty, you don't have to enter it
     * @param tableName
     * @param selectedFields
     * @param wherefields
     * @throws SQLException
     */
    public void selectData(String tableName, String selectedFields, String... wherefields) throws SQLException {
        System.out.printf("%-20s", "nodeId");
        System.out.printf("%-10s", "xcoord");
        System.out.printf("%-10s","ycoord");
        System.out.printf("%-10s","floor");
        System.out.printf("%-20s","building");
        System.out.printf("%-10s","nodeType");
        System.out.printf("%-40s","longName");
        System.out.printf("%-15s","shortName");
        System.out.printf("%-10s","teamAssigned");
        System.out.println();
        String str="";
        Statement stmt = connection.createStatement();
        if (wherefields.length==0) {
            str = "select " + selectedFields + " from " + tableName;
        }
        else {
            str = "select " + selectedFields + " from " + tableName+" where "+wherefields[0];
        }

        ResultSet resultSet = stmt.executeQuery(str);

        while (resultSet.next()){

            System.out.printf("%-20s",resultSet.getString("nodeId"));
            System.out.printf("%-10d",resultSet.getInt("xcoord"));
            System.out.printf("%-10d",resultSet.getInt("ycoord"));
            System.out.printf("%-10d",resultSet.getInt("floor"));
            System.out.printf("%-20s",resultSet.getString("building"));
            System.out.printf("%-10s",resultSet.getString("nodeType"));
            System.out.printf("%-40s",resultSet.getString("longName"));
            System.out.printf("%-15s",resultSet.getString("shortName"));
            System.out.printf("%-10s",resultSet.getString("teamAssigned"));

            System.out.println();
        }

}

    /**
     * Reads a CSV file
     * @param filePath
     * @return an ArrayList that contains all the elements in the original CSV file (without the file header)
     */
    public ArrayList<String[]> readCsvFile(String filePath) {
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            reader.readHeaders(); //Jump over the file header

            while (reader.readRecord()) {
                csvList.add(reader.getValues()); //Read in line by line and add to the arrayList
            }
            reader.close();
            //System.out.println("Total lines��" + csvList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvList;
    }

    /**
     * Transfer the CSV ArrayList into separate Nodes
     * @param als
     * @return an ArrayList that contains all the Nodes
     */


    /**
     * Creates a Node table
     * @throws SQLException
     */
    public void createNodeTable() throws SQLException {
        createTable("Node", "nodeId char(10), xcoord int, ycoord int, floor int, building varchar(20), " +
                "nodeType char(4), longName varchar(50), shortName varchar(20), teamAssigned char(6)");
    }

    /**
     * Inserts individual Nodes into the Node table
     * @param tableName
     * @param node
     * @throws SQLException
     * @throws IllegalAccessException
     */


    /**
     * Initializes the DataBase
     * @throws SQLException
     */
    public void setup() throws SQLException{
        System.out.println("-------Embedded Apache Derby Connection Testing --------");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Apache Derby Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the database JAR is located");
            System.out.println("Click OK, now you can compile your program and run it.");
            e.printStackTrace();
            return;
        }
        System.out.println("Apache Derby driver registered!");
        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }
        System.out.println("Apache Derby connection established!");
    }

    public void closeConn(Connection conn,Statement stmt){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeConn(Connection conn,Statement stmt,ResultSet rs){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
