package br.com.ada3.cadcliente.controller;

import br.com.ada3.cadcliente.dto.request.ClienteSalvarRequestDto;
import br.com.ada3.cadcliente.dto.response.ClienteSalvarResponseDto;
import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteSalvarResponseDto salvar(@RequestBody ClienteSalvarRequestDto requestDto) {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(requestDto.getNome());
        clienteModel.setEmail(requestDto.getEmail());

        clienteModel = clienteService.salva(clienteModel);

        ClienteSalvarResponseDto responseDto = new ClienteSalvarResponseDto();
        responseDto.setId(clienteModel.getId());
        responseDto.setNome(clienteModel.getNome());
        responseDto.setEmail(clienteModel.getEmail());

        return responseDto;
    }

}
