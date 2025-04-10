package com.example.emprestimo_livro.Service;

import com.example.emprestimo_livro.Dto.ClienteDto;
import com.example.emprestimo_livro.Entity.Cliente;
import com.example.emprestimo_livro.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto save(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setSobrenome(clienteDto.getSobrenome());
        cliente.setCpf(clienteDto.getCpf());

        // Salva o cliente na base de dados
        cliente = clienteRepository.save(cliente);

        // Converte o cliente salvo para ClienteDto e retorna
        return new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpf());
    }

    public Optional<ClienteDto> getClienteDtoById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return Optional.of(new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpf()));
        }
        return Optional.empty();
    }

    public boolean clienteExists(Long id) {
        return clienteRepository.existsById(id);
    }

    public boolean updateClienteNome(Long id, String novoNome) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(novoNome);
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
