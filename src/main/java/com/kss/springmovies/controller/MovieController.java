package com.kss.springmovies.controller;

import com.kss.springmovies.dto.Movie;
import com.kss.springmovies.repository.IMovieRepository;
import com.kss.springmovies.repository.MovieExistsException;
import com.kss.springmovies.repository.MovieNotFoundException;
import com.kss.springmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MovieController {
    private final IMovieRepository movieRepository;

    @Autowired
    public MovieController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("movies")
    public String createMovie(@RequestParam String title, Model model) throws MovieExistsException {
        Movie movie = new Movie(title);
//        movieRepository.addMovie(movie);
        movieRepository.save(movie);
        model.addAttribute("movie", movie);
        return "result";
    }

    @GetMapping("movies")
    public String readAllMovies(Model model) {
//        model.addAttribute("moviesVariable", movieRepository.getAllMovies());
        model.addAttribute("moviesVariable", movieRepository.findAll());
        return "movies";
    }

    @GetMapping("movies/{id}")
    public String readMovie(@PathVariable("id") Integer id, Model model) throws MovieNotFoundException {
//        model.addAttribute("movie", movieRepository.getMovie(id));

        model.addAttribute("movie", movieRepository
                .findById(id)
                // jesli w optionalu jest warto to zwroc wartosc jesli nie ma to rzuc exception
                .orElseThrow(() -> new MovieNotFoundException()));
        return "movie";
    }

    @PutMapping("movies/{id}")
    public void changeMovieTitle(@PathVariable("id") Integer id, @RequestParam("title") String title) throws MovieNotFoundException {
//        movieRepository.getMovie(id).setTitle(title);
    }

    @DeleteMapping("movies/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) throws MovieNotFoundException {
//        movieRepository.deleteMovie(id);
        movieRepository.deleteById(id);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    public String movieNotFound() {
        return "404";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(MovieExistsException.class)
    public String movieExists() {
        return "409";
    }
}
