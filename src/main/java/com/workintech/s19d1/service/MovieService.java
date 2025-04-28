package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie save(Movie movie);
    Movie findById(long id);
    void delete(Movie movie);
}
