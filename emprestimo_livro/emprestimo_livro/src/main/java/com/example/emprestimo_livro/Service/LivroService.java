package com.example.emprestimo_livro.Service;

import com.example.emprestimo_livro.Dto.LivroDto;
import com.example.emprestimo_livro.Entity.Livro;
import com.example.emprestimo_livro.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro fromDTO(LivroDto livroDto) {
        Livro livro = new Livro();
        livro.setId(livroDto.getId());
        livro.setNome(livroDto.getNome());
        livro.setAutor(livroDto.getAutor());
        livro.setIsbn(livroDto.getIsbn());
        livro.setGenero(livroDto.getGenero());
        return livro;
    }

    public LivroDto toDto(Livro livro) {
        LivroDto livroDto = new LivroDto();
        livroDto.setId(livro.getId());
        livroDto.setNome(livro.getNome());
        livroDto.setAutor(livro.getAutor());
        livroDto.setIsbn(livro.getIsbn());
        livroDto.setGenero(livro.getGenero());
        return livroDto;
    }

    public LivroDto save(LivroDto livroDto) {
        Livro livro = fromDTO(livroDto);
        livro = livroRepository.save(livro);
        return toDto(livro);
    }

    public Optional<LivroDto> getLivroDtoById(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.map(this::toDto);
    }

    public boolean livroExists(Long id) {
        return livroRepository.existsById(id);
    }

    public boolean updateLivro(Long id, LivroDto livroDto) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            livro.setNome(livroDto.getNome());
            livro.setAutor(livroDto.getAutor());
            livro.setIsbn(livroDto.getIsbn());
            livro.setGenero(livroDto.getGenero());

            livroRepository.save(livro);
            return true;
        }
        return false;
    }

    public boolean deleteLivro(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
