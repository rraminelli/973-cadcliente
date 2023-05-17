package br.com.ada3.cadcliente.service;

import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteModel salva(ClienteModel clienteModel) {
        if (clienteModel.getNome() == null) {
            throw new RuntimeException("Nome é obrigatório");
        }
        if (clienteModel.getEmail() == null) {
            throw new RuntimeException("Email é obrigatório");
        }
        return clienteRepository.save(clienteModel);
    }

    public void excluir(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public ClienteModel getById(long idCliente) {
        ClienteModel clienteModel = clienteRepository.findById(idCliente).orElse(null);
        if (clienteModel == null) {
            throw new RuntimeException("ID nao encontrado");
        }
        return clienteModel;
    }

}
