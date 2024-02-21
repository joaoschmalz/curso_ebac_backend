package org.example.exercises.m30_project3.exceptions;

public class DAOException extends Exception {

  private static final long serialVersionUID = 7054379063290825137L;

  public DAOException(String msg, Exception e) {
    super(msg, e);
  }
}
