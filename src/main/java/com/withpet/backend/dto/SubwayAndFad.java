package com.withpet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubwayAndFad {
//    private List<Subway> subways;
//    private List<Fad> fads;

    private String searchValue;
    private String searchType;
    private String x;
    private String y;

}
