package com.eurovision.exercise.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Cities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {
  @Id
  private Integer id;

  private String name;
}
