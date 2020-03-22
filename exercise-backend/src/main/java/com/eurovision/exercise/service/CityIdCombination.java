package com.eurovision.exercise.service;

import com.eurovision.exercise.repository.CityEntity;
import com.eurovision.exercise.repository.CityDao;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Combinations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CityIdCombination {

  private final CityDao cityDao;

  List<Integer> getLongestIdSequence() {
    List<Integer> cityIdList = cityDao.findTop30ByOrderByNameAsc().stream()
        .map(CityEntity::getId)
        .collect(Collectors.toList());

    List<Integer> cityIdIndexList = cityIdList.stream().sorted().map(cityId -> getIndexForId(cityId, cityIdList)).collect(Collectors.toList());
    List<Integer> longestIdCombination = getLongestSortedIdCombination(cityIdIndexList);

    return longestIdCombination.stream().map(index -> cityIdList.get(index)).collect(Collectors.toList());
  }

  private List<Integer> getLongestSortedIdCombination(List<Integer> integerList) {
    for(Integer subsetNumber = integerList.size(); subsetNumber >= 0; subsetNumber--) {
      log.info("Working with {} elements", subsetNumber);
      Iterator<int[]> iterator = new Combinations(integerList.size(), subsetNumber).iterator();

      while(iterator.hasNext()) {
        List<Integer> currentCombination = Arrays.stream(iterator.next()).boxed().collect(Collectors.toList());
        if(compareArray(integerList, currentCombination)) {
          return currentCombination;
        }
      }
    }
    return null;
  }

  private Integer getIndexForId(Integer initialValue, List<Integer> cityIdList) {
    return IntStream.range(0, cityIdList.size())
        .filter(index -> initialValue.equals(cityIdList.get(index)))
        .findFirst().getAsInt();
  }

  private boolean compareArray(List<Integer> sourceList, List<Integer> compareList) {
    boolean matchesOrder = true;
    Integer previousPosition = -1;
    Iterator<Integer> iterator = compareList.iterator();
    while(iterator.hasNext() && matchesOrder) {
      Integer currentPosition = iterator.next();
      Integer foundPosition = getIndexForId(currentPosition, sourceList);
      if(foundPosition > previousPosition) {
        previousPosition = foundPosition;
      } else {
        matchesOrder = false;
      }
    }
    return matchesOrder;
  }
}
