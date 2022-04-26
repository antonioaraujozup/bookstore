package br.com.zup.edu.bookstore.api.livro;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> mensagens = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return ResponseEntity.badRequest().body(mensagens);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        String constraintName = ex.getConstraintName();

        String message;
        if (constraintName.equals("UK_LIVRO_ISBN")) {
            message = "Produto já cadastrado";
        } else {
            message = "Foi violada uma exceção de unicidade do banco de dados";
        }

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "UNPROCESSABLE ENTITY",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", message
        );

        return ResponseEntity.unprocessableEntity().body(body);

    }
}
