package org.example.exercises.m30_project3.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

  String dbName();
  String setJavaName();
}
