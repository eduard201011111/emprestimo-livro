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

    // Converte ClienteDto para Cliente
    public Cliente fromDTO(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setSobrenome(clienteDto.getSobrenome());
        cliente.setCpf(clienteDto.getCpf());
        return cliente;
    }

    // Converte Cliente para ClienteDto
    public ClienteDto toDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNome(cliente.getNome());
        clienteDto.setSobrenome(cliente.getSobrenome());
        clienteDto.setCpf(cliente.getCpf());
        return clienteDto;
    }

    // Salva um Cliente a partir de um ClienteDto
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = fromDTO(clienteDto);
        return clienteRepository.save(cliente);
    }

    // Método que busca um Cliente por ID e retorna um Optional<ClienteDto>
    public Optional<ClienteDto> getClienteDtoById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        // Retorna um Optional<ClienteDto>, caso cliente não seja encontrado retorna Optional.empty()
        return clienteOptional.map(this::toDto);
    }

    // Método que verifica se um Cliente existe com base no ID e retorna um boolean
    public boolean clienteExists(Long id) {
        return clienteRepository.existsById(id);  // Verifica se o cliente existe
    }

    // Método que atualiza o nome do Cliente e retorna um boolean
    public boolean updateClienteNome(Long id, String novoNome) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(novoNome);
            clienteRepository.save(cliente);  // Salva o cliente com o novo nome
            return true;  // Retorna true caso a atualização tenha sido bem-sucedida
        }
        return false;  // Retorna false caso o cliente não exista
    }

    // Método que tenta deletar um cliente e retorna um boolean indicando o sucesso
    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);  // Deleta o cliente
            return true;  // Retorna true se a exclusão foi bem-sucedida
        }
        return false;  // Retorna false se o cliente não foi encontrado
    }
}
