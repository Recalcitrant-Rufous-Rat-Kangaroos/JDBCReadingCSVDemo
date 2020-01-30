package edu.wpi.derbydemo;

/**
 * Created by Xiaowei Chen
 * This is the version of Databse that inserts
 */
import java.sql.*;
import java.util.ArrayList;
import com.csvreader.CsvReader;
public class DerbyDemo {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        System.out.println("John1");
        DBController db = new DBController();

        db.setup();
        db.dropTable("Node");
        db.createNodeTable();

        ArrayList<String[]> al = db.readCsvFile("src/MapBnodes.csv");
        ArrayList<Node> alnode = db.arrNode(al);
        for (int i = 0; i < alnode.size(); i++){
            db.insertNode("Node", alnode.get(i));
        }
        db.selectData("Node", "*");
    }
}
