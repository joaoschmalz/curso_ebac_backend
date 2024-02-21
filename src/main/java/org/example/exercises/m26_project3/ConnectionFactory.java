package org.example.exercises.m26_project3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class ConnectionFactory {

  private static Connection connection;
  private ConnectionFactory(Connection connection) {

  }

  public static Connection getConnection() {
    if (isNull(connection)) {
      connection = initConnection();
    }
    return connection;
  }

  private static Connection initConnection() {
    try {
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
