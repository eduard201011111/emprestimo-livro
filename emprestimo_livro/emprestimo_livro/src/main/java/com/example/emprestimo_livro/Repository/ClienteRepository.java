package com.example.emprestimo_livro.Repository;

import com.example.emprestimo_livro.Entity.Cliente;
import com.example.emprestimo_livro.Entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
