package org.example.exercises.m32_jpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_course")
  @SequenceGenerator(name = "sequence_course", sequenceName = "sq_course", initialValue = 1, allocationSize = 1)
  private Long id;
  @Column(name ="code", length = 10, nullable = false, unique = true)
  private String code;
  @Column(name = "name", length = 255, nullable = false)
  private String name;
  @Column(name = "description", length = 400, nullable = false)
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
