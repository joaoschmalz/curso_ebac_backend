package org.example.exercises.m32_jpa;

import org.example.exercises.m32_jpa.dao.IRegistrationDAO;
import org.example.exercises.m32_jpa.dao.RegistrationDAO;
import org.example.exercises.m32_jpa.domain.Registration;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;

public class RegistrationTest {

  private final IRegistrationDAO dao = new RegistrationDAO();

  @Test
  public void save() {
    Registration registration = new Registration();

    registration.setCode("A1");
    registration.setCreatedAt(Instant.now());
    registration.setActive(true);
    registration.setValue(200d);

    dao.save(registration);

    assertNotNull(registration);
    assertNotNull(registration.getId());
  }
}
