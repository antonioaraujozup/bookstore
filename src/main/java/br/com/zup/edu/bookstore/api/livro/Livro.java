package br.com.zup.edu.bookstore.api.livro;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_LIVRO_ISBN", columnNames = {"ISBN"})
})
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String ISBN;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    public Livro(String titulo, String ISBN, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.dataPublicacao = dataPublicacao;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Livro() {
    }

    public Long getId() {
        return id;
    }
}
