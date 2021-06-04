package com.business.Tests;

import com.business.Utilities.ConfigReader;
import com.business.Utilities.DBUtils;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

  /*                   ResultSet metadata
    We get data of data, we get more data, so that we can know how many columns,
       row in the result.We can get column names existing in result.
        We can get all values certain column.
        using ResultSetMetadata interface we created our DBUtil class methods.
        We can either use this way or directly call any methods from DBUtil
        class and store the data relevantly in required Data structure and
        do our analyze. */


public class JDBC_ResultSetMetadata_beginner {

    //Connection info:
    //jdbc:postgresql:(jdbc db driver)//ip where db is : 5432(port) + /hr (dbname)
    private final String hrdbURL = ConfigReader.getProperty("hrdbUrl");
    private final String hrdbUsername = ConfigReader.getProperty("hrdbUser");  //hr
    private final String hrdbPassword = ConfigReader.getProperty("hrdbPassword");  //hr


    @Test
    public void usingResultSetMetaData() throws SQLException {
        DBUtils.createConnection(hrdbURL, hrdbUsername, hrdbPassword);
        String query = "select * from employees;";
        ResultSet resultset = DBUtils.executeQuery(query);
        ResultSetMetaData rsMetadata = resultset.getMetaData();
        // gets total columns count in result
        System.out.println("Columns count " + rsMetadata.getColumnCount());
        // gets column name by the given index 2
        System.out.println("Column name of 2 " + rsMetadata.getColumnName(2));
        // get all column names
        for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
            System.out.print(rsMetadata.getColumnName(i) + " " + "\t");
        }
        System.out.println();
        DBUtils.destroy();
    }


}
