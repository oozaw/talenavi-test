package wahyu_ady.talenavi_test.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import wahyu_ady.talenavi_test.model.WebResponse;

@RestControllerAdvice
public class ErrorController {

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception) {
      return ResponseEntity
         .status(HttpStatus.BAD_REQUEST)
         .body(WebResponse.<String>builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.name())
            .errors(exception.getMessage())
            .build()
         );
   }

   @ExceptionHandler(ResponseStatusException.class)
   public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
      return ResponseEntity
         .status(exception.getStatusCode())
         .body(WebResponse.<String>builder()
            .code(exception.getStatusCode().value())
            .status(HttpStatus.valueOf(exception.getStatusCode().value()).name())
            .errors(exception.getReason())
            .build()
         );
   }
}
