package com.example.emprestimo_livro.Repository;

import com.example.emprestimo_livro.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
