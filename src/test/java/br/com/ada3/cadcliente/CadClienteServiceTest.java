package br.com.ada3.cadcliente;

import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

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

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ClienteModel clienteModelDeleted = clienteService.getById(idCliente);
        });

        Assertions.assertEquals("ID nao encontrado", exception.getMessage());

    }

    @Test
    void get_id_invalido() {

        Exception exception = Assertions.assertThrows(Exception.class, () -> {

            ClienteModel clienteModel = clienteService.getById(100);

        });

        Assertions.assertEquals("ID nao encontrado", exception.getMessage());

    }

    @Test
    void email_obrigatorio() {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome("Cliente test");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ClienteModel clienteModelSaved = clienteService.salva(clienteModel);
        });

        Assertions.assertEquals("Email é obrigatório", exception.getMessage());

    }

    @Test
    void nome_obrigatorio() {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setEmail("test@test.com");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ClienteModel clienteModelSaved = clienteService.salva(clienteModel);
        });

        Assertions.assertEquals("Nome é obrigatório", exception.getMessage());

    }

    @Test
    void get_by_id() {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome("Cliente test");
        clienteModel.setEmail("test@test.com");

        ClienteModel clienteModelSaved = clienteService.salva(clienteModel);

        Long idCliente = clienteModelSaved.getId();

        ClienteModel clienteModelGet = clienteService.getById(idCliente);

        Assertions.assertEquals(clienteModelSaved.getId(), clienteModelGet.getId());
        Assertions.assertEquals(clienteModelSaved.getNome(), clienteModelGet.getNome());
        Assertions.assertEquals(clienteModelSaved.getEmail(), clienteModelGet.getEmail());

    }

}
