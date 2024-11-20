package wahyu_ady.talenavi_test.service;

import wahyu_ady.talenavi_test.dto.CreateMovieRequest;
import wahyu_ady.talenavi_test.dto.UpdateMovieRequest;
import wahyu_ady.talenavi_test.model.MovieResponse;

import java.util.List;

public interface MovieService {
   MovieResponse create(CreateMovieRequest createMovieRequest);

   MovieResponse update(Integer id, UpdateMovieRequest updateMovieRequest);

   void delete(Integer id);

   List<MovieResponse> search(String title);

   List<MovieResponse> getAll();

   MovieResponse detail(Integer id);
}
