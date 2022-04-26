package br.com.zup.edu.bookstore.api.livro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoLivroController {

    private final LivroRepository repository;

    public CadastrarNovoLivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/livros")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoLivroRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByISBN(request.getISBN())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Livro já cadastrado");
        }

        Livro livro = request.paraLivro();

        repository.save(livro);

        URI location = uriComponentsBuilder.path("/livros/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
