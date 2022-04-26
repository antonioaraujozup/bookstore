package br.com.zup.edu.bookstore.api.livro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @ISBN
    @JsonProperty("isbn")
    private String ISBN;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

    public NovoLivroRequest(String titulo, String ISBN, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.dataPublicacao = dataPublicacao;
    }

    public NovoLivroRequest() {
    }

    public Livro paraLivro(){
        return new Livro(titulo,ISBN,dataPublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
}
