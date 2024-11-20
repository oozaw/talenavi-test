package wahyu_ady.talenavi_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wahyu_ady.talenavi_test.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
