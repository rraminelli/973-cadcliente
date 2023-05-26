package br.com.ada3.cadcliente;

import br.com.ada3.cadcliente.dto.request.ClienteSalvarRequestDto;
import br.com.ada3.cadcliente.dto.response.ClienteResponseDto;
import br.com.ada3.cadcliente.dto.response.ClienteSalvarResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadClienteRestControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void inserir_cliente_rest() {

        ClienteSalvarRequestDto requestDto = new ClienteSalvarRequestDto();
        requestDto.setNome("cliente test");
        requestDto.setEmail("test@test.com");

        ResponseEntity<ClienteSalvarResponseDto> responseDto =
                restTemplate.exchange(
                "/clientes",
                HttpMethod.POST,
                new HttpEntity<>(requestDto),
                ClienteSalvarResponseDto.class);

        Assertions.assertEquals(201, responseDto.getStatusCode().value());
        Assertions.assertNotNull(responseDto.getBody());
        Assertions.assertNotNull(responseDto.getBody().getId());
        Assertions.assertEquals(requestDto.getNome(), responseDto.getBody().getNome());
        Assertions.assertEquals(requestDto.getEmail(), responseDto.getBody().getEmail());

    }

    @Test
    void get_cliente_by_id() {

        ClienteSalvarRequestDto requestDto = new ClienteSalvarRequestDto();
        requestDto.setNome("cliente test");
        requestDto.setEmail("test@test.com");

        ResponseEntity<ClienteSalvarResponseDto> responseDto =
                restTemplate.exchange(
                        "/clientes",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDto),
                        ClienteSalvarResponseDto.class);

        Long idCliente = responseDto.getBody().getId();

        ResponseEntity<ClienteResponseDto> responseGetDto =
                restTemplate.exchange(
                        "/clientes/" + idCliente,
                        HttpMethod.GET,
                        null,
                        ClienteResponseDto.class);

        ClienteResponseDto responseBody = responseGetDto.getBody();

        Assertions.assertEquals(200, responseGetDto.getStatusCode().value());
        Assertions.assertEquals(idCliente, responseBody.getId());
        Assertions.assertEquals(requestDto.getNome(), responseBody.getNome());
        Assertions.assertEquals(requestDto.getEmail(), responseBody.getEmail());


    }

    @Test
    void atualizar_cliente() {

        ClienteSalvarRequestDto requestDto = new ClienteSalvarRequestDto();
        requestDto.setNome("cliente test atualizar");
        requestDto.setEmail("test@test.com");

        ResponseEntity<ClienteSalvarResponseDto> responseDto =
                restTemplate.exchange(
                        "/clientes",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDto),
                        ClienteSalvarResponseDto.class);

        Long idCliente = responseDto.getBody().getId();

        requestDto.setNome("nome atualizado");
        requestDto.setEmail("teste@atualizado.com");

        ResponseEntity<ClienteSalvarResponseDto> responsePutDto =
                restTemplate.exchange(
                        "/clientes/" + idCliente,
                        HttpMethod.PUT,
                        new HttpEntity<>(requestDto),
                        ClienteSalvarResponseDto.class);


        Assertions.assertEquals(200, responsePutDto.getStatusCode().value());
        Assertions.assertEquals(idCliente, responsePutDto.getBody().getId());
        Assertions.assertEquals(requestDto.getNome(), responsePutDto.getBody().getNome());
        Assertions.assertEquals(requestDto.getEmail(), responsePutDto.getBody().getEmail());

    }

    @Test
    void excluir_cliente() {

        ClienteSalvarRequestDto requestDto = new ClienteSalvarRequestDto();
        requestDto.setNome("cliente test excluir");
        requestDto.setEmail("test@test.com");

        ResponseEntity<ClienteSalvarResponseDto> responseDto =
                restTemplate.exchange(
                        "/clientes",
                        HttpMethod.POST,
                        new HttpEntity<>(requestDto),
                        ClienteSalvarResponseDto.class);

        Long idCliente = responseDto.getBody().getId();

        ResponseEntity<?> responseDeleteDto =
                restTemplate.exchange(
                "/clientes/" + idCliente,
                HttpMethod.DELETE,
                null,
                Object.class
        );

        Assertions.assertEquals(202, responseDeleteDto.getStatusCode().value());

        ResponseEntity<ClienteResponseDto> responseGetDto =
                restTemplate.exchange(
                        "/clientes/" + idCliente,
                        HttpMethod.GET,
                        null,
                        ClienteResponseDto.class);

        Assertions.assertEquals(404, responseGetDto.getStatusCode().value());


    }

}