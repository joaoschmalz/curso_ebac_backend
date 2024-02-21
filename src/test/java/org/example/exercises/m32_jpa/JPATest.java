package org.example.exercises.m32_jpa;

import org.example.exercises.m32_jpa.dao.CourseDAO;
import org.example.exercises.m32_jpa.dao.ICourseDAO;
import org.example.exercises.m32_jpa.domain.Course;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JPATest {

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
