package wahyu_ady.talenavi_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wahyu_ady.talenavi_test.model.GenreResponse;
import wahyu_ady.talenavi_test.model.WebResponse;
import wahyu_ady.talenavi_test.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

   @Autowired
   private GenreService genreService;

   @GetMapping(
      path = "/api/v1/genres",
      produces = "application/json"
   )
   public ResponseEntity<WebResponse<List<GenreResponse>>> getAll() {
      List<GenreResponse> genreResponses = genreService.getAll();

      return ResponseEntity.ok(
         WebResponse.<List<GenreResponse>>builder()
            .code(200)
            .status("OK")
            .data(genreResponses)
            .build()
      );
   }
}
