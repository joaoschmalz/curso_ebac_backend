package org.example.exercises.m30_project3.exceptions;

public class DuplicatedEntryException extends Exception {

  private static final long serialVersionUID = -7509649433607067138L;

  public DuplicatedEntryException(String msg) {
    super(msg);
  }
}
