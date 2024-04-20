package io.github.the_sdet.db;

import io.github.the_sdet.logger.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * This is a utility class to handle all the database querying operations needed
 * for validations against database
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class DatabaseUtils {
  private final Connection connection;
  private Statement statement;

  /**
   * Initializes Database utils
   * 
   * @param connection
   *            JDBC Connection Object
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public DatabaseUtils(Connection connection) {
    this.connection = connection;
  }

  /**
   * This method establishes a connection to Database if the connection is NOT yet
   * established
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private void initializeConnectionToDb() {
    try {
      assert connection != null;
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    } catch (SQLException e) {
      Log.error("Exception creating statement using connection object passed...", e);
    }
  }

  /**
   * This method closes the established Database connection
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void closeDatabaseConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        Log.error("Error closing the DB connection...", e);
      }
    }
    Log.info("Closed Database Connection...");
  }

  /**
   * This method executes an SQL query and returns the result with the column
   * names
   *
   * @param sql
   *            sql statement to query the DB
   * @return A list of rows are returned where each row is returned as a
   *         LinkedHashMap
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<LinkedHashMap<String, String>> readDataFromDbWithColumnNames(String sql) {
    Log.info("Query: " + sql);
    initializeConnectionToDb();
    assert statement != null;
    ResultSet resultSet = null;
    try {
      resultSet = statement.executeQuery(sql);
    } catch (SQLException e) {
      Log.error("Error executing the query: " + sql + "\n", e);
    }
    assert resultSet != null;
    return readResultSetWithColumnNames(resultSet);
  }

  /**
   * This method executes an SQL query and returns the result without the column
   * names
   *
   * @param sql
   *            sql statement to query the DB
   * @return A list of rows are returned where each row is returned as a List
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<List<String>> readDataFromDb(String sql) {
    Log.info("Query: " + sql);
    initializeConnectionToDb();
    ResultSet resultSet = null;
    try {
      resultSet = statement.executeQuery(sql);
    } catch (SQLException e) {
      Log.error("Error executing the query: " + sql + "\n", e);
    }
    assert resultSet != null;
    return readResultSet(resultSet);
  }

  /**
   * This method executes an SQL query and returns only the first row with the
   * column names
   *
   * @param sql
   *            sql statement to query the DB
   * @return Only one row is returned as a LinkedHashMap
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public LinkedHashMap<String, String> readSingleRowFromDbWithColumnNames(String sql) {
    return readDataFromDbWithColumnNames(sql).get(0);
  }

  /**
   * This method executes an SQL query and returns only the first row without the
   * column names
   *
   * @param sql
   *            sql statement to query the DB
   * @return Only one row is returned as a List
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<String> readSingleRowFromDb(String sql) {
    List<List<String>> dataFromDb = readDataFromDb(sql);
    if (dataFromDb.isEmpty()) {
      Log.error("No records fetched from DB for query: " + sql);
      return new ArrayList<>();
    } else
      return readDataFromDb(sql).get(0);
  }

  /**
   * This method executes an SQL query and returns only the data at first position
   * of first row as a String
   *
   * @param sql
   *            sql statement to query the DB
   * @return Single String value from position [0][0]
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String readSingleDataFromDb(String sql) {
    List<String> dataFromDb = readSingleRowFromDb(sql);
    return dataFromDb.isEmpty() ? null : readSingleRowFromDb(sql).get(0);
  }

  /**
   * This method executes an SQL query and returns the first column data without
   * the column names
   *
   * @param sql
   *            sql statement to query the DB
   * @return First column data is returned as a List
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<String> readSingleColumnFromDb(String sql) {
    List<String> valuesFromSingleColumn = new ArrayList<>();
    List<List<String>> dataFromDb = readDataFromDb(sql);
    for (List<String> row : dataFromDb) {
      valuesFromSingleColumn.add(row.get(0));
    }
    return valuesFromSingleColumn;
  }

  /**
   * This method is a utility method to parse the resultSet to List of
   * LinkedHashMap format
   *
   * @param resultSet
   *            ResultSet object
   * @return A list of rows are returned where each row is returned as a
   *         LinkedHashMap
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private List<LinkedHashMap<String, String>> readResultSetWithColumnNames(ResultSet resultSet) {
    List<LinkedHashMap<String, String>> result = new ArrayList<>();
    try {
      resultSet.beforeFirst();
      while (resultSet.next()) {
        LinkedHashMap<String, String> row = new LinkedHashMap<>();
        int columns = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columns; i++) {
          String key = resultSet.getMetaData().getColumnName(i);
          String value;
          if (resultSet.getMetaData().getColumnType(i) == Types.VARBINARY) {
            value = String.valueOf(resultSet.getObject(i, UUID.class));
          } else {
            value = String.valueOf(resultSet.getObject(i));
          }
          row.put(key, value);
        }
        result.add(row);
      }
    } catch (SQLException e) {
      Log.error("Error parsing the result-set...", e);
    }
    Log.info("Data fetched from DB: " + result);
    return result;
  }

  /**
   * This method is a utility method to parse the resultSet to List<List<String>>
   * format
   *
   * @param resultSet
   *            ResultSet object
   * @return A list of rows are returned where each row is returned as a List
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private List<List<String>> readResultSet(ResultSet resultSet) {
    List<List<String>> result = new ArrayList<>();
    try {
      resultSet.beforeFirst();
      while (resultSet.next()) {
        List<String> row = new ArrayList<>();
        int columns = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columns; i++) {
          String value;
          if (resultSet.getMetaData().getColumnType(i) == Types.VARBINARY) {
            value = String.valueOf(resultSet.getObject(i, UUID.class));
          } else {
            value = String.valueOf(resultSet.getObject(i));
          }
          row.add(value);
        }
        result.add(row);
      }
    } catch (SQLException e) {
      Log.error("Error parsing the result-set...", e);
    }
    Log.info("Data fetched from DB: " + result);
    return result;
  }
}