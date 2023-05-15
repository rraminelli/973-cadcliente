package br.com.ada3.cadcliente;

import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadClienteApplicationTests {

    final ClienteService clienteService;

    CadClienteApplicationTests(@Autowired ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Test
    @DisplayName("Test - Inserir cliente - OK!")
    void inserir_cliente_ok() {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome("Cliente test 2");
        clienteModel.setEmail("test@test.com");

        ClienteModel clienteModelSaved = clienteService.salva(clienteModel);

        Assertions.assertNotNull(clienteModelSaved.getId());
        Assertions.assertEquals(clienteModel.getNome(), clienteModelSaved.getNome());
        Assertions.assertEquals(clienteModel.getEmail(), clienteModelSaved.getEmail());

    }

    @Test
    void excluir_cliente() {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome("Cliente test");
        clienteModel.setEmail("test@test.com");

        ClienteModel clienteModelSaved = clienteService.salva(clienteModel);

        Long idCliente = clienteModelSaved.getId();

        clienteService.excluir(idCliente);

        ClienteModel clienteModelDeleted = clienteService.getById(1L);

        Assertions.assertNull(clienteModelDeleted);

    }

}
