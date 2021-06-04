package com.business.Tests;

import com.business.Utilities.DBUtils;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBC_MySQL {

    private final String url = "jdbc:mysql://127.0.0.1:3306/world"; // dbName here is must
    private final String username = "root";
    private final String password = "anar";

    @Test
    public void testingMySqlDb_Table_City() throws SQLException {
        DBUtils.createConnection(url, username, password);
        String query = "Select * from city";
        // 1. As ResultSet
        ResultSet resultSet = DBUtils.executeQuery(query);
        while (resultSet.next()) {
            System.out.println("cityName--> " + resultSet.getString("Name") + " | population-->" + resultSet.getInt("Population"));
        }
        // 2. As List - All values in certain column
        List<String> districtColumn = DBUtils.executeQueryAndGetColumnValuesAsList(query, "District");
        for (String district : districtColumn) {
            System.out.println(district);
        }
        // 3. As ListOfLists - All values by each row
        List<List<Object>> valuesByRow = DBUtils.executeQueryAndGetResultAsListOfLists(query);
        for (int i = 0; i < valuesByRow.size(); i++) {
            System.out.println(valuesByRow.get(i).toString());
        }




    }


}
