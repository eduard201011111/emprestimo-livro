package com.example.emprestimo_livro.Dto;

import com.example.emprestimo_livro.Entity.Emprestimo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class LivroDto {


    @JsonIgnoreProperties("Livros")
    private Emprestimo emprestimo;

    private long id;
    private String nome;
    private String autor;
    private long isbn;
    private String genero;

}
