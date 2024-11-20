package wahyu_ady.talenavi_test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import wahyu_ady.talenavi_test.dto.CreateMovieRequest;
import wahyu_ady.talenavi_test.entity.Genre;
import wahyu_ady.talenavi_test.model.MovieResponse;
import wahyu_ady.talenavi_test.model.WebResponse;
import wahyu_ady.talenavi_test.repository.GenreRepository;
import wahyu_ady.talenavi_test.repository.MovieRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MovieControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private MovieRepository movieRepository;

   @Autowired
   private GenreRepository genreRepository;

   @BeforeEach
   void setUp() {
      movieRepository.deleteAll();
   }

   @Test
   void testCreateSuccess() throws Exception {
      // save the genre
      Genre genre = new Genre();
      genre.setName("Fantasy");
      genreRepository.save(genre);

      Genre genre2 = new Genre();
      genre2.setName("Adventure");
      genreRepository.save(genre2);

      Set<Integer> genres = Set.of(genre.getId(), genre2.getId());

      log.info("Genres: {}", genres);

      CreateMovieRequest newMovie = new CreateMovieRequest();
      newMovie.setTitle("Harry Potter");
      newMovie.setDirector("David Yates");
      newMovie.setSummary("A young wizard finds out he is a wizard");
      newMovie.setGenres(genres);

      mockMvc.perform(
         post("/api/v1/movies")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(newMovie))
      ).andExpectAll(
         status().isCreated()
      ).andDo(result -> {
         log.info(result.getResponse().getContentAsString());

         WebResponse<MovieResponse> response = objectMapper.readValue(
            result.getResponse().getContentAsString(),
            new TypeReference<>() {}
         );

         assertNull(response.getErrors());
         assertEquals(newMovie.getTitle(), response.getData().getTitle());
      });
   }
}
