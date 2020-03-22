package com.eurovision.exercise.web.model;

import com.eurovision.exercise.repository.CityEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
  private String id;
  private String name;


  public static List<City> fromEntity(List<CityEntity> cityEntityList) {
    return cityEntityList.stream().map(City::fromEntity).collect(Collectors.toList());
  }


  public static City fromEntity(CityEntity cityEntity) {
    return City.builder()
        .id(cityEntity.getId().toString())
        .name(cityEntity.getName())
        .build();
  }
}
