package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null ) {
            throw new ApiException("Movie is not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return movie;
    }

    @PostMapping()
    public Movie save(@RequestBody Movie movie, Actor actor) {
        movieService.save(movie);
        actor.addMovie(movie);
        return movie;
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            throw new ApiException("Movie could not be found", HttpStatus.NOT_FOUND);
        }
        movieService.delete(movie);
        return movie;
    }
}
