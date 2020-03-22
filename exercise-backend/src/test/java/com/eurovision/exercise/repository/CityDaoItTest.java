package com.eurovision.exercise.repository;

import com.eurovision.exercise.ExerciseApplicationItTest;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Slf4j
public class CityDaoItTest extends ExerciseApplicationItTest {

  @Autowired
  private CityDao cityDao;

  @Test
  public void testFindTopByOrderByNameAscIdDesc() {
    List<CityEntity> cityList = cityDao.findTop30ByOrderByNameAsc();

    Assert.assertNotNull(cityList);
    Assert.assertEquals(30, cityList.size());

    IntStream.range(1, cityList.size()).forEach(index -> {
      log.info("{}", cityList.get(index).getId());
      Assert.assertTrue(
          cityList.get(index).getName()
              .compareTo(cityList.get(index - 1).getName()) > 0);
    });
  }

  @Test
  public void testFindPaginatedByOrderByNameAscIdDesc() {
    Page<CityEntity> cityPage = cityDao.findAll(PageRequest.of(0,10));

    Assert.assertNotNull(cityPage);
    Assert.assertEquals(0, cityPage.getNumber());
    Assert.assertEquals(10, cityPage.getNumberOfElements());
    Assert.assertEquals(10, cityPage.getSize());
    Assert.assertEquals(cityDao.count(), cityPage.getTotalElements());
    Assert.assertEquals((cityDao.count() / 10) + 1, cityPage.getTotalPages());
  }

  @Test
  public void testFindByNameContainingIgnoreCaseOrderByNameAsc() {
    List<CityEntity> cityList = cityDao.findByNameContainingIgnoreCaseOrderByNameAsc("ab");

    Assert.assertNotNull(cityList);
    Assert.assertTrue(cityList.size() > 0);
    cityList.forEach(cityEntity -> {
      Assert.assertTrue(cityEntity.getName().toLowerCase().contains("ab"));
    });
  }
}
