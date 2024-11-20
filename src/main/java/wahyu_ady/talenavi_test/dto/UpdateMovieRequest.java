package wahyu_ady.talenavi_test.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMovieRequest {
      @Size(max = 100)
      private String title;

      @Size(max = 100)
      private String director;

      @Size(max = 100)
      private String summary;

      private Set<Integer> genres;
}
