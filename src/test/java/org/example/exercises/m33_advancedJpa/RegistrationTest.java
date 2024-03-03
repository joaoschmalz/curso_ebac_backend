package org.example.exercises.m33_advancedJpa;

import org.example.exercises.m33_advancedJpa.dao.*;
import org.example.exercises.m33_advancedJpa.domain.Course;
import org.example.exercises.m33_advancedJpa.domain.Registration;
import org.example.exercises.m33_advancedJpa.domain.Student;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegistrationTest {

  private final ICourseDAO courseDAO = new CourseDAO();
  private final IStudentDAO studentDAO = new StudentDAO();
  private final IRegistrationDAO registrationDAO = new RegistrationDAO();

  @Test
  public void save() {
    Course course = this.createCourse("Course Test");
    Student student = this.createStudent("Xablau");
    Registration registration = new Registration();

    registration.setCode("A1");
    registration.setCreatedAt(Instant.now());
    registration.setActive(true);
    registration.setValue(200d);
    registration.setCourse(course);
    registration.setStudent(student);

    registrationDAO.save(registration);

    student.setRegistration(registration);

    assertNotNull(registration);
    assertNotNull(registration.getId());
    assertEquals(registration.getId(), student.getRegistration().getId());
  }

  private Course createCourse(String code) {
    Course course = new Course();

    course.setCode(code);
    course.setDescription("Description Test");
    course.setName("Name Test");
    return courseDAO.save(course);
  }

  private Student createStudent(String name) {
    Student student = new Student();

    student.setName(name);
    student.setActive(true);

    return studentDAO.save(student);
  }
}
