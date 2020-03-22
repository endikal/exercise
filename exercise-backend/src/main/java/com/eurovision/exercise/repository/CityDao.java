package com.eurovision.exercise.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<CityEntity, Integer> {

  List<CityEntity> findTop30ByOrderByNameAsc();

  List<CityEntity> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
  Page<CityEntity> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

}
