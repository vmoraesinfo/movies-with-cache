package com.moraes.movies.DTO.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String id;
    private String primaryTitle;
    private Integer startYear;
    private String titleType;
    private Image primaryImage;

}
