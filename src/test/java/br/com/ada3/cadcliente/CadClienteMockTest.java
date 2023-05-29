package br.com.ada3.cadcliente;

import br.com.ada3.cadcliente.controller.ClienteRestController;
import br.com.ada3.cadcliente.dto.request.ClienteSalvarRequestDto;
import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ClienteRestController.class)
public class CadClienteMockTest {

    @MockBean
    ClienteService clienteService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void salvar_cliente_test_controller() throws Exception {

        ClienteSalvarRequestDto clienteSalvarRequestDto = new ClienteSalvarRequestDto();
        clienteSalvarRequestDto.setNome("test mock");
        clienteSalvarRequestDto.setEmail("test@test.com");

        ClienteModel clienteModelEntrada = new ClienteModel();
        clienteModelEntrada.setNome(clienteSalvarRequestDto.getNome());
        clienteModelEntrada.setEmail(clienteSalvarRequestDto.getEmail());

        ClienteModel clienteModelSaida = new ClienteModel();
        clienteModelSaida.setNome(clienteModelEntrada.getNome());
        clienteModelSaida.setEmail(clienteModelEntrada.getEmail());
        clienteModelSaida.setId(10L);

        Mockito.when(clienteService.salva(clienteModelEntrada))
                .thenReturn(clienteModelSaida);

        String json = objectMapper.writeValueAsString(clienteSalvarRequestDto);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/clientes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(clienteModelSaida.getId()), Long.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is(clienteModelSaida.getNome()), String.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(clienteModelSaida.getEmail()), String.class));
    }

    @Test
    void get_cliente_by_id() throws Exception {

        long idCliente = 5;

        ClienteModel clienteModelSaida = new ClienteModel();
        clienteModelSaida.setNome("cliente test");
        clienteModelSaida.setEmail("email@test.com");
        clienteModelSaida.setId(idCliente);

        Mockito.when(clienteService.getById(anyLong())).thenReturn(clienteModelSaida);

        this.mockMvc.perform(
                    MockMvcRequestBuilders
                        .get("/clientes/" + 5)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(clienteModelSaida.getId()), Long.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is(clienteModelSaida.getNome()), String.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(clienteModelSaida.getEmail()), String.class));

    }

}
