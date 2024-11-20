package wahyu_ady.talenavi_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wahyu_ady.talenavi_test.dto.CreateMovieRequest;
import wahyu_ady.talenavi_test.dto.UpdateMovieRequest;
import wahyu_ady.talenavi_test.model.MovieResponse;
import wahyu_ady.talenavi_test.model.WebResponse;
import wahyu_ady.talenavi_test.service.MovieService;

import java.util.List;

@RestController
public class MovieController {

   @Autowired
   private MovieService movieService;

   @PostMapping(
      path = "/api/v1/movies",
      consumes = "application/json",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<MovieResponse>> create(@RequestBody CreateMovieRequest createMovieRequest) {
      MovieResponse movieResponse = movieService.create(createMovieRequest);

      return ResponseEntity.status(HttpStatus.CREATED).body(
         WebResponse.<MovieResponse>builder()
            .code(HttpStatus.CREATED.value())
            .status(HttpStatus.CREATED.name())
            .data(movieResponse)
            .build()
      );
   }

   @PutMapping(
      path = "/api/v1/movies/{id}",
      consumes = "application/json",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<MovieResponse>> update(@PathVariable Integer id, @RequestBody UpdateMovieRequest updateMovieRequest) {
      MovieResponse movieResponse = movieService.update(id, updateMovieRequest);

      return ResponseEntity.status(HttpStatus.OK).body(
         WebResponse.<MovieResponse>builder()
            .code(HttpStatus.OK.value())
            .status(HttpStatus.OK.name())
            .data(movieResponse)
            .build()
      );
   }

   @DeleteMapping(
      path = "/api/v1/movies/{id}",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<String>> delete(@PathVariable Integer id) {
      movieService.delete(id);

      return ResponseEntity.status(HttpStatus.OK).body(
         WebResponse.<String>builder()
            .code(HttpStatus.OK.value())
            .status(HttpStatus.OK.name())
            .data("Movie with id " + id + " successfully deleted")
            .build()
      );
   }

   @GetMapping(
      path = "/api/v1/movies",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<List<MovieResponse>>> getAll() {
      List<MovieResponse> movieResponses = movieService.getAll();

      return ResponseEntity.status(HttpStatus.OK).body(
         WebResponse.<List<MovieResponse>>builder()
            .code(HttpStatus.OK.value())
            .status(HttpStatus.OK.name())
            .data(movieResponses)
            .build()
      );
   }

   @GetMapping(
      path = "/api/v1/movies/{id}",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<MovieResponse>> detail(@PathVariable Integer id) {
      MovieResponse movieResponse = movieService.detail(id);

      return ResponseEntity.status(HttpStatus.OK).body(
         WebResponse.<MovieResponse>builder()
            .code(HttpStatus.OK.value())
            .status(HttpStatus.OK.name())
            .data(movieResponse)
            .build()
      );
   }

   @GetMapping(
      path = "/api/v1/movies/search",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<List<MovieResponse>>> search(@RequestParam String keyword) {
      List<MovieResponse> movieResponses = movieService.search(keyword);

      return ResponseEntity.status(HttpStatus.OK).body(
         WebResponse.<List<MovieResponse>>builder()
            .code(HttpStatus.OK.value())
            .status(HttpStatus.OK.name())
            .data(movieResponses)
            .build()
      );
   }
}
