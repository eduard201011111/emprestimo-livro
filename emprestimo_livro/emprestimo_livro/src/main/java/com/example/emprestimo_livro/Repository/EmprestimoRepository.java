package com.example.emprestimo_livro.Repository;

import com.example.emprestimo_livro.Entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {



}
