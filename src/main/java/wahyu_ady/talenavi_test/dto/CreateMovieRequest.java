package wahyu_ady.talenavi_test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateMovieRequest {
   @NotBlank
   @Size(max = 100)
   private String title;

   @NotBlank
   @Size(max = 100)
   private String director;

   @NotBlank
   @Size(max = 100)
   private String summary;

   @NotNull
   private Set<Integer> genres;
}
