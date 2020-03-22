package com.eurovision.exercise.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

  private Integer pageNumber;
  private Integer pageSize;
  private Long totalNumber;
  private Integer totalPages;

  public static Pagination fromEntity(Page page) {
    return Pagination.builder()
        .pageNumber(page.getNumber())
        .pageSize(page.getSize())
        .totalPages(page.getTotalPages())
        .totalNumber(page.getTotalElements())
        .build();
  }
}