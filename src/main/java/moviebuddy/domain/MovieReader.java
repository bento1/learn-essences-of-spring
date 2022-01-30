package moviebuddy.domain;

import java.util.List;

import moviebuddy.Movie;

public interface MovieReader {
    List<Movie> loadMovies();
}