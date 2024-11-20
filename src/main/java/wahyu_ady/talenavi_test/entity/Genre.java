package wahyu_ady.talenavi_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String name;

   @Column(name = "created_at")
   private LocalDateTime createdAt;

   @Column(name = "updated_at")
   private LocalDateTime updatedAt;

   @ManyToMany()
   @JoinTable(
       name = "movie_genres",
       joinColumns = @JoinColumn(name = "genre_id"),
       inverseJoinColumns = @JoinColumn(name = "movie_id")
   )
   @JsonIgnore
   private List<Movie> movies = new ArrayList<>();
}
