package br.com.zup.edu.bookstore.api.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    boolean existsByISBN(String isbn);
}
