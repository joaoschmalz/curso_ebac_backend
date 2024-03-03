package org.example.exercises.m33_advancedJpa.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  @SequenceGenerator(name = "student_sequence", sequenceName = "sq_student", initialValue = 1, allocationSize = 1)
  private Long id;
  @OneToOne(mappedBy = "student")
  private Registration registration;
  @Column(name = "name", length = 255, nullable = false)
  private String name;
  @Column(name = "active", nullable = false)
  private boolean active;
  @ManyToMany
  @JoinTable(name = "device_student",
      joinColumns = { @JoinColumn(name = "student_id")},
      inverseJoinColumns = { @JoinColumn(name = "device_id")})
  private List<Device> devices;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Registration getRegistration() {
    return registration;
  }

  public void setRegistration(Registration registration) {
    this.registration = registration;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public List<Device> getDevices() {
    return devices;
  }

  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }
}
