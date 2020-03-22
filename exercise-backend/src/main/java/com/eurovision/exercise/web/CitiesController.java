package com.eurovision.exercise.web;

import com.eurovision.exercise.repository.CityDao;
import com.eurovision.exercise.web.model.CitiesResponse;
import java.util.Optional;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CitiesController {

  static final String REQUEST_MAPPING = "/cities";

  private final CityDao cityDao;

  @GetMapping
  @RequestMapping(value = CitiesController.REQUEST_MAPPING, produces = APPLICATION_JSON_VALUE)
  public CitiesResponse getCities(
      @RequestParam(name = "name") Optional<String> name,
      @RequestParam(name = "page") @Min(0) Optional<Integer> pageNumber,
      @RequestParam(name = "size") @Min(1) Optional<Integer> pageSize) {
    if(name.isPresent()) {
      if(pageNumber.isPresent() && pageSize.isPresent()) {
        return CitiesResponse.fromEntity(cityDao.findByNameContainingIgnoreCaseOrderByNameAsc(name.get(), PageRequest.of(pageNumber.get(), pageSize.get())));
      } else {
        return CitiesResponse.fromEntity(cityDao.findByNameContainingIgnoreCaseOrderByNameAsc(name.get()));
      }
    }
    if(pageNumber.isPresent() && pageSize.isPresent()) {
      return CitiesResponse.fromEntity(cityDao.findAll(PageRequest.of(pageNumber.get(), pageSize.get())));
    } else {
      return CitiesResponse.fromEntity(cityDao.findAll());
    }
  }
}