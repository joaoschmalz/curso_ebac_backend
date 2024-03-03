package org.example.exercises.m33_advancedJpa;

import org.example.exercises.m33_advancedJpa.dao.CourseDAO;
import org.example.exercises.m33_advancedJpa.dao.ICourseDAO;
import org.example.exercises.m33_advancedJpa.domain.Course;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CourseTest {

  private final ICourseDAO dao = new CourseDAO();
  @Test
  public void save() {
    Course course = new Course();

    course.setCode("A1");
    course.setDescription("Description Test");
    course.setName("Name Test");
    dao.save(course);

    assertNotNull(course);
    assertNotNull(course.getId());
  }
}
