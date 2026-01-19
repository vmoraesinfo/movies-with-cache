package com.moraes.movies.DTO.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Integer page;
    private Integer next;
    private List<Movie> titles;
}
