package com.example.emprestimo_livro.Controller;

import com.example.emprestimo_livro.Dto.ClienteDto;
import com.example.emprestimo_livro.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
        // Chamando o método de save no serviço para salvar o cliente e retornar o ClienteDto
        ClienteDto savedClienteDto = clienteService.save(clienteDto);

        // Verifica se o cliente foi salvo corretamente
        if (savedClienteDto != null) {
            return new ResponseEntity<>(savedClienteDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        Optional<ClienteDto> clienteDto = clienteService.getClienteDtoById(id);
        return clienteDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> clienteExists(@PathVariable Long id) {
        boolean exists = clienteService.clienteExists(id);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClienteNome(@PathVariable Long id, @RequestBody String novoNome) {
        boolean updated = clienteService.updateClienteNome(id, novoNome);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deleted = clienteService.deleteCliente(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
