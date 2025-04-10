package com.example.emprestimo_livro.Controller;

import com.example.emprestimo_livro.Dto.LivroDto;
import com.example.emprestimo_livro.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestBody LivroDto livroDto) {
        LivroDto savedLivroDto = livroService.save(livroDto);
        if (savedLivroDto != null) {
            return new ResponseEntity<>(savedLivroDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> getLivroById(@PathVariable Long id) {
        Optional<LivroDto> livroDto = livroService.getLivroDtoById(id);
        return livroDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> livroExists(@PathVariable Long id) {
        boolean exists = livroService.livroExists(id);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLivro(@PathVariable Long id, @RequestBody LivroDto livroDto) {
        boolean updated = livroService.updateLivro(id, livroDto);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        boolean deleted = livroService.deleteLivro(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
