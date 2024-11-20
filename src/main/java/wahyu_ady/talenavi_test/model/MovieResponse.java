package wahyu_ady.talenavi_test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wahyu_ady.talenavi_test.entity.Genre;
import wahyu_ady.talenavi_test.entity.Movie;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
   private Integer id;

   private String title;

   private String director;

   private String summary;

   private Set<Genre> genres;

   private LocalDateTime createdAt;

   private LocalDateTime updatedAt;

   public static MovieResponse fromEntity(Movie movie) {
      return MovieResponse.builder()
         .id(movie.getId())
         .title(movie.getTitle())
         .director(movie.getDirector())
         .summary(movie.getSummary())
         .genres(movie.getGenres())
         .createdAt(movie.getCreatedAt())
         .updatedAt(movie.getUpdatedAt())
         .build();
   }
}
