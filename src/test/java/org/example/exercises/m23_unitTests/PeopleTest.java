package org.example.exercises.m23_unitTests;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class PeopleTest {

  @Test
  public void ensureItCanFilterByWomen() {

    People people = new People();
    List<Person> women = people.filterOnlyWomen();
    assertFalse(women.stream().anyMatch(w -> w.getGender() == 'M'));
  }
}
