package wahyu_ady.talenavi_test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import wahyu_ady.talenavi_test.entity.Genre;
import wahyu_ady.talenavi_test.model.GenreResponse;
import wahyu_ady.talenavi_test.repository.GenreRepository;

import java.util.List;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService {
   @Autowired
   private GenreRepository genreRepository;

   @Autowired
   private ValidationService validationService;

   @Override
   public List<GenreResponse> getAll() {
      List<Genre> genres = genreRepository.findAll();

      if (genres.isEmpty()) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No genre found");
      }

      return genres.stream()
         .map(GenreResponse::fromEntity)
         .toList();
   }
}
