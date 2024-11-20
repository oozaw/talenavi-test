package wahyu_ady.talenavi_test.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import wahyu_ady.talenavi_test.dto.CreateMovieRequest;
import wahyu_ady.talenavi_test.dto.UpdateMovieRequest;
import wahyu_ady.talenavi_test.entity.Genre;
import wahyu_ady.talenavi_test.entity.Movie;
import wahyu_ady.talenavi_test.model.MovieResponse;
import wahyu_ady.talenavi_test.repository.GenreRepository;
import wahyu_ady.talenavi_test.repository.MovieRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

   @Autowired
   private MovieRepository movieRepository;

   @Autowired
   private GenreRepository genreRepository;

   @Autowired
   private ValidationService validationService;

   @Transactional
   @Override
   public MovieResponse create(CreateMovieRequest createMovieRequest) {
      validationService.validate(createMovieRequest);

      // save movie to database
      Movie movie = new Movie();
      movie.setTitle(createMovieRequest.getTitle());
      movie.setDirector(createMovieRequest.getDirector());
      movie.setSummary(createMovieRequest.getSummary());

      // save genres to the movie
      Set<Integer> genres = createMovieRequest.getGenres();
      for (Integer genreId : genres) {
         Genre genre = genreRepository.findById(genreId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));

         movie.getGenres().add(genre);
      }

      movieRepository.save(movie);

      return MovieResponse.fromEntity(movie);
   }

   @Transactional
   @Override
   public MovieResponse update(Integer id, UpdateMovieRequest updateMovieRequest) {
      validationService.validate(updateMovieRequest);

      Movie movie = movieRepository.findById(id)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

      movie.setTitle(updateMovieRequest.getTitle());
      movie.setDirector(updateMovieRequest.getDirector());
      movie.setSummary(updateMovieRequest.getSummary());

      // clear existing genres
      movie.getGenres().clear();

//      movieRepository.save(movie);

      // save genres to the movie
      Set<Integer> genres = updateMovieRequest.getGenres();
      for (Integer genreId : genres) {
         Genre genre = genreRepository.findById(genreId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));

         movie.getGenres().add(genre);
      }

      movieRepository.save(movie);

      return MovieResponse.fromEntity(movie);
   }

   @Transactional
   @Override
   public void delete(Integer id) {
      Movie movie = movieRepository.findById(id)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

      movieRepository.delete(movie);
   }

   @Override
   public List<MovieResponse> search(String keyword) {
      List<Movie> movies = movieRepository.findByTitleContainsIgnoreCase(keyword);

      if (movies.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found");
      }

      return movies.stream().map(MovieResponse::fromEntity).toList();
   }

   @Override
   public List<MovieResponse> getAll() {
      List<Movie> movies = movieRepository.findAll();

      if (movies.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found");
      }

      return movies.stream().map(MovieResponse::fromEntity).toList();
   }

   @Override
   public MovieResponse detail(Integer id) {
      Movie movie = movieRepository.findById(id)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

      return MovieResponse.fromEntity(movie);
   }
}
