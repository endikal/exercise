package com.eurovision.exercise.service;

import com.eurovision.exercise.ExerciseApplicationItTest;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CityIdCombinationTest extends ExerciseApplicationItTest {

  @Autowired
  private CityIdCombination cityIdCombination;

  @Test
  public void testGetLongestIdSequence() {
    List<Integer> longestCombination = cityIdCombination.getLongestIdSequence();

    log.info("Received combination {}", longestCombination);

    Assert.assertNotNull(longestCombination);
    Assert.assertTrue(longestCombination.size() > 0);

    Iterator<Integer> iterator =  longestCombination.iterator();
    longestCombination.stream().sorted().forEach(cityId ->
        Assert.assertEquals(cityId, iterator.next())
    );
  }
}
