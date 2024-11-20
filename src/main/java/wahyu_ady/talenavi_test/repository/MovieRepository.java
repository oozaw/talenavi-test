package wahyu_ady.talenavi_test.repository;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wahyu_ady.talenavi_test.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

   @Query("select m from Movie m where upper(m.title) like upper(concat('%', ?1, '%'))")
   List<Movie> findByTitleContainsIgnoreCase(@Nullable String title);
}
