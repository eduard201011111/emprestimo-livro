package com.example.emprestimo_livro.Service;

import com.example.emprestimo_livro.Dto.EmprestimoDtoRequest;
import com.example.emprestimo_livro.Dto.EmprestimoDtoResponse;
import com.example.emprestimo_livro.Entity.Emprestimo;
import com.example.emprestimo_livro.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo fromDTO(EmprestimoDtoRequest emprestimoDtoRequest) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(emprestimoDtoRequest.getId());
        emprestimo.setData_inicial(emprestimoDtoRequest.getData_inicial());
        emprestimo.setData_final(emprestimoDtoRequest.getData_final());
        return emprestimo;
    }

    public EmprestimoDtoResponse toDto(Emprestimo emprestimo) {
        EmprestimoDtoResponse emprestimoDtoResponse = new EmprestimoDtoResponse();
        emprestimoDtoResponse.setId(emprestimo.getId());
        emprestimoDtoResponse.setId(emprestimo.getId());
        emprestimoDtoResponse.setData_inicial(emprestimo.getData_inicial());
        emprestimoDtoResponse.setData_final(emprestimo.getData_final());
        return emprestimoDtoResponse;
    }

    public EmprestimoDtoResponse save(EmprestimoDtoRequest emprestimoDtoRequest) {
        Emprestimo emprestimo = fromDTO(emprestimoDtoRequest);
        emprestimo = emprestimoRepository.save(emprestimo);
        return toDto(emprestimo);
    }

    public Optional<EmprestimoDtoResponse> getEmprestimoDtoById(Long id) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        return emprestimoOptional.map(this::toDto);
    }

    public boolean emprestimoExists(Long id) {
        return emprestimoRepository.existsById(id);
    }

    public boolean updatedata_final(Long id, EmprestimoDtoRequest emprestimo1) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setData_final(emprestimo.getData_final());
            emprestimoRepository.save(emprestimo);
            return true;
        }
        return false;
    }

    public boolean deleteEmprestimo(Long id) {
        if (emprestimoRepository.existsById(id)) {
            emprestimoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
