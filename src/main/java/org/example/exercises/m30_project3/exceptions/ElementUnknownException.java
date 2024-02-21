package org.example.exercises.m30_project3.exceptions;

public class ElementUnknownException extends Exception{

  private static final long serialVersionUID = -2268140970978666251L;
  public ElementUnknownException(String msg) {
    this(msg, null);
  }
  public ElementUnknownException(String msg, Throwable e) {
    super(msg, e);
  }

}
