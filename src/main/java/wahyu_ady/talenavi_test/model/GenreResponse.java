package wahyu_ady.talenavi_test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wahyu_ady.talenavi_test.entity.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreResponse {
   private Integer id;

   private String name;

   public static GenreResponse fromEntity(Genre genre) {
      return GenreResponse.builder()
         .id(genre.getId())
         .name(genre.getName())
         .build();
   }
}
