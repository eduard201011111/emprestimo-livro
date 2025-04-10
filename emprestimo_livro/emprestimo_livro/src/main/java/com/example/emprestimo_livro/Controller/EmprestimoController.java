package com.example.emprestimo_livro.Controller;


import com.example.emprestimo_livro.Dto.EmprestimoDtoRequest;
import com.example.emprestimo_livro.Dto.EmprestimoDtoResponse;
import com.example.emprestimo_livro.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

    @RestController
    @RequestMapping("/emprestimos")
    public class EmprestimoController {

        @Autowired
        private EmprestimoService emprestimoService;

        @PostMapping
        public ResponseEntity<EmprestimoDtoResponse> save(@RequestBody EmprestimoDtoRequest emprestimoDtoRequest) {
            EmprestimoDtoResponse savedEmprestimoDto = emprestimoService.save(emprestimoDtoRequest);
            if (savedEmprestimoDto != null) {
                return new ResponseEntity<>(savedEmprestimoDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<EmprestimoDtoResponse> getEmprestimoById(@PathVariable Long id) {
            Optional<EmprestimoDtoResponse> emprestimoDtoResponse = emprestimoService.getEmprestimoDtoById(id);
            return emprestimoDtoResponse.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        @GetMapping("/exists/{id}")
        public ResponseEntity<Boolean> emprestimoExists(@PathVariable Long id) {
            boolean exists = emprestimoService.emprestimoExists(id);
            return ResponseEntity.ok(exists);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Void> updateDataFinal(@PathVariable Long id, @RequestBody String novaDataFinal) {
            boolean updated = emprestimoService.updatedata_final(id, novaDataFinal);
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEmprestimo(@PathVariable Long id) {
            boolean deleted = emprestimoService.deleteEmprestimo(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
