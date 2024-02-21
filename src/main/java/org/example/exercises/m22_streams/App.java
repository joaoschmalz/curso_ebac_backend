package org.example.exercises.m22_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {

    final List<Person> people = populateInitialList();

    final List<Person> women = people.stream().filter(p -> p.getGender() == 'F').collect(Collectors.toList());

    for (Person w : women) {
      System.out.println(w);
    }

  }

  private static List<Person> populateInitialList() {
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

    return people;
  }
}
