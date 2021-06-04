package com.business.Utilities;

import java.sql.*;
import java.util.*;
import java.util.Map;

public class DBUtils {
    /**
     * NOTE: In any class before calling any method from DBUtil class,
     * 1st we need to call createConnection class and pass
     * connectionURL, username, password as parameter.
     * Only after that we can call other methods from DBUtil class.
     */

    //Connection info for Hr DB -> PostgreSQL database:
    //jdbc:postgresql:(jdbc db driver)//ip where db is : 5432(port) + /hr (dbname)
    private static String hrdbURL = ConfigReader.getProperty("hrdbUrl");
    private static String hrdbUsername = ConfigReader.getProperty("hrdbUser");  //hr
    private static String hrdbPassword = ConfigReader.getProperty("hrdbPassword");  //hr


    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;

    private DBUtils() {

    }

    /**
     * This method takes parameters from util class itself, works for specific DB.
     */
    public static void createConnectionToHrDB() {
        try {
            connection = DriverManager.getConnection(hrdbURL, hrdbUsername, hrdbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*Always must be called 1st in any class.*/
    public static void createConnection(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Always at the end of test must be closed connection by calling this method.*/
    public static void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns : ResultSet resultSet .
     * Store returned date into ResultSet resultSet object.
     * Accepts : query as String as method parameter
     */
    public static ResultSet executeQuery(String query) {
        try {   // here we enable backward/forward retrieving function
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    /**
     * Returns int;  --> total rows count in the result.
     * Store returned data into int;
     * Accepts : resultSet as parameter.
     */
    public static int executeQueryAndGetRowsCount(String query) {
        int amountOfRows = 0;
        try {
            executeQuery(query);
            resultSet.last();
            amountOfRows = resultSet.getRow();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return amountOfRows;
    }

    /**
     * Returns int;  ->total columns count in the result
     * Store returned data into int;
     * Accepts : query as String.
     */
    public static int executeQueryAndGetColumnsCount(String query) {
        int columnsCount = 0;
        executeQuery(query);
        try {
            rsmd = resultSet.getMetaData();
            columnsCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return columnsCount;
    }


    /**
     * Returns List<String> anyName
     * Store returned date into List<String>columnNames
     * Accepts query as String as method parameter
     */
    public static List<String> executeQueryAndGetColumnsNamesAsList(String query) {
        List<String> columns = new ArrayList<>();
        try {
            executeQuery(query);
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return columns;
    }

    /**
     * Returns : List <String>  (values in the certain column)
     * Store returned data as  List <String>.
     * Accepts: query as parameter and columnName
     */
    public static List<String> executeQueryAndGetColumnValuesAsList(String query, String columnName) {
        List<String> values = new ArrayList<>();
        try {
            executeQuery(query);
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                values.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return values;
    }

    /**
     * Returns List<List<Object>>
     * Store the result in List<List<Object>>
     * Will return each row as list
     * Accept query String as a parameter.
     */
    public static List<List<Object>> executeQueryAndGetResultAsListOfLists(String query) {
        List<List<Object>> rowList = new ArrayList<>();
        try {
            executeQuery(query);
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * Returns List of Maps
     * Store result in List of Map , use loop to get values.
     * Accepts: query String as parameter
     */

    public static List<Map<String, Object>> executeQueryAndGetResultAsListOfMaps(String query) {
        List<Map<String, Object>> rowList = new ArrayList<>();
        try {
            executeQuery(query);
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    public static boolean verifyEmployeeExists(String firstName, String lastName) {
        boolean exists = false;
        String query = "SELECT COUNT(*) as count \n" +
                "FROM employees\n" +
                "WHERE first_name = '" + firstName + "' and  last_name='" + lastName + "';";
        executeQuery(query);
        try {
            resultSet.next();
            exists = resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


    public static Object getCellValue(String query) {
        return executeQueryAndGetResultAsListOfLists(query).get(0).get(0);
    }


    public static List<Object> getRowList(String query) {
        return executeQueryAndGetResultAsListOfLists(query).get(0);
    }


    public static Map<String, Object> getRowMap(String query) {
        return executeQueryAndGetResultAsListOfMaps(query).get(0);
    }
}
