package org.example.exercises.m25_project2.exceptions;

public class KeyTypeNotFoundException extends Exception {

  private static final long serialVersionUID = -1389494676398525746L;

  public KeyTypeNotFoundException(String msg) {
    this(msg, null);
  }

  public KeyTypeNotFoundException(String msg, Throwable e) {
    super(msg, e);
  }
}
