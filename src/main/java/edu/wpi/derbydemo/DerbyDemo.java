package edu.wpi.derbydemo;

/**
 * Created by Xiaowei Chen
 * This is the version of Databse that inserts
 */
import java.sql.*;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import utils.JDBCutils;

public class DerbyDemo {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

        JDBCutils db = new JDBCutils();




        ArrayList<String[]> al = db.readCsvFile("src/MapBnodes.csv");
        for (String[] array : al) {// This loop is used to iterate through the arraylist
            for (String element : array) {//This loop is used to iterate through the array inside the arraylist
                System.out.println(element);
            }
        }


}





}

