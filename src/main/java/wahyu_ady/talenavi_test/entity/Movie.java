package wahyu_ady.talenavi_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      private String title;

      private String director;

      private String summary;

      @Column(name = "created_at")
      private LocalDateTime createdAt;

      @Column(name = "updated_at")
      private LocalDateTime updatedAt;

      @ManyToMany()
      @JoinTable(
          name = "movie_genres",
          joinColumns = @JoinColumn(name = "movie_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id")
      )
      private Set<Genre> genres = new HashSet<>();
}
