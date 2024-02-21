package org.example.exercises.m23_unitTests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class People {

  private List<Person> people;

  public People() {
    final List<Person> people = new ArrayList<>();

    people.add(new Person("Alan", 'M', "alan@email.com"));
    people.add(new Person("Bruna", 'F', "bruna@email.com"));
    people.add(new Person("Carol", 'F', "carol@email.com"));
    people.add(new Person("Denis", 'M', "denis@email.com"));
    people.add(new Person("Fernando", 'M', "fernando@email.com"));
    people.add(new Person("Gabriela", 'F', "gabriela@email.com"));
    people.add(new Person("Julia", 'F', "julia@email.com"));
    people.add(new Person("Gilberto", 'M', "gilberto@email.com"));
    people.add(new Person("Juliana", 'F', "juliana@email.com"));
    people.add(new Person("Eduarda", 'F', "eduarda@email.com"));

    this.people = people;
  }

  public List<Person> filterOnlyWomen() {
    if (isNull(this.people)) return new ArrayList<>();

    return this.people.stream().filter(p -> p.getGender() == 'F').collect(Collectors.toList());
  }

  public List<Person> getPeople() {
    return people;
  }

  public void setPeople(List<Person> people) {
    this.people = people;
  }
}
