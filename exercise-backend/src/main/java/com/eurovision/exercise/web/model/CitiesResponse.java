package com.eurovision.exercise.web.model;

import com.eurovision.exercise.repository.CityEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesResponse {
  private List<City> cities;
  private Pagination pagination;

  public static CitiesResponse fromEntity(List<CityEntity> cityEntityList) {
    return CitiesResponse.builder()
        .cities(City.fromEntity(cityEntityList))
        .build();
  }

  public static CitiesResponse fromEntity(Page page) {
    return CitiesResponse.builder()
        .cities(City.fromEntity(page.getContent()))
        .pagination(Pagination.fromEntity(page))
        .build();
  }
}
