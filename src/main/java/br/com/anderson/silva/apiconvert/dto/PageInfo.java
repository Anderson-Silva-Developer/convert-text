package br.com.anderson.silva.apiconvert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    private int totalResults;
    private int resultsPerPage;
}
