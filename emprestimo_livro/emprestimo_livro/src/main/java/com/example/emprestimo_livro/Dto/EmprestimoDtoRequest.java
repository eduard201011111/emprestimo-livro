package com.example.emprestimo_livro.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmprestimoDtoRequest {

    private long id;
    private Date data_inicial;
    private Date data_final;
}
