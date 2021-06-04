package com.business.Tests;

import com.business.Utilities.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBC_tasks_using_DBUtils {
    /**
     * Connect to DB
     * Verify employee id 105 belongs to Kamil Austin;
     */
    private static final Logger log = LogManager.getLogger(JDBC_tasks_using_DBUtils.class.getName());

    @Test
    public void nameTestById() throws SQLException {
        DBUtils.createConnectionToHrDB();
        String query = "select first_name,last_name from employees where employee_id=105;";
        ResultSet resultSet = DBUtils.executeQuery(query);
        resultSet.next();
        String name = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Assert.assertEquals(name, "Kamil");
        Assert.assertEquals(lastName, "Austin");
        // Get result as List of Maps
        List<Map<String, Object>> resultAsListOfMaps = DBUtils.executeQueryAndGetResultAsListOfMaps(query);
        Object name2 = resultAsListOfMaps.get(0).get("first_name");
        Object lastName2 = resultAsListOfMaps.get(0).get("last_name");
        Assert.assertEquals(name2, "Kamil");
        Assert.assertEquals(lastName2, "Austin");

    }

    /**
     * Find how many people is IT_PROG job_id
     * Verify records count is 6
     * Verify list of Names
     * Verify Samir is IT_PROG
     * Verify Samir's lastname is Babayev
     */
    @Test
    public void nestedQuery() {
        DBUtils.createConnectionToHrDB();
        String query = "select first_name , last_name, job_id from employees where job_id ='IT_PROG';";
        int resultRowCount = DBUtils.executeQueryAndGetRowsCount(query);
        Assert.assertEquals(resultRowCount, 8);
        // get all values from certain column as List and verify certain name exist there
        List<String> names = DBUtils.executeQueryAndGetColumnValuesAsList(query, "first_name");
        Assert.assertTrue(names.contains("Samir"));
        // verify Samir's lastName is Babayev
        String lastName = "";
        List<Map<String, Object>> resAsListOfMaps = DBUtils.executeQueryAndGetResultAsListOfMaps(query);
        for (int i = 0; i < resAsListOfMaps.size(); i++) {
            if (resAsListOfMaps.get(i).get("first_name").equals("Samir")) {
                lastName += resAsListOfMaps.get(i).get("last_name").toString();
                break;
            }
        }
        log.info("lastname is " + lastName);
        Assert.assertEquals(lastName, "Babayev");

        // get whole result as List of Lists (all rows)
        List<List<Object>> resAsLisOfLists = DBUtils.executeQueryAndGetResultAsListOfLists(query);
        for (int i = 0; i < resAsLisOfLists.size(); i++) {
            System.out.println(resAsLisOfLists.get(i));
        }


    }
}



