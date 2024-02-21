package org.example.exercises.m30_project3.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class ConnectionFactory {

  private static Connection connection;

  private ConnectionFactory(final Connection connection) {

  }

  public static Connection getConnection() throws SQLException {
    if (isNull(connection) || connection.isClosed()) {
      connection = initConnection();
    }
    return connection;
  }

  private static Connection initConnection() {
    try {
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebac_p3", "postgres", "password");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
