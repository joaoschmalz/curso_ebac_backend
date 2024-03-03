package org.example.exercises.m33_advancedJpa.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Device")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_sequence")
  @SequenceGenerator(name = "device_sequence", sequenceName = "sq_device", initialValue = 1, allocationSize = 1)
  private Long id;
  @Column(name = "code", length = 10, nullable = false, unique = true)
  private String code;
  @Column(name = "description", length = 255, nullable = false)
  private String description;
  @ManyToMany(mappedBy = "devices")
  private List<Student> students;
}
