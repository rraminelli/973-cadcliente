package br.com.ada3.cadcliente.controller;

import br.com.ada3.cadcliente.dto.request.ClienteSalvarRequestDto;
import br.com.ada3.cadcliente.dto.response.ClienteResponseDto;
import br.com.ada3.cadcliente.dto.response.ClienteSalvarResponseDto;
import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteSalvarResponseDto create(@RequestBody ClienteSalvarRequestDto requestDto) {

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDto> findAll() {

        List<ClienteResponseDto> responseDtoList = new ArrayList<>();

        List<ClienteModel> clienteModelList = clienteService.listarTodos();

        clienteModelList.forEach(clienteModel -> {
            ClienteResponseDto clienteResponseDto = new ClienteResponseDto();
            clienteResponseDto.setId(clienteModel.getId());
            clienteResponseDto.setEmail(clienteModel.getEmail());
            clienteResponseDto.setNome(clienteModel.getNome());
            responseDtoList.add(clienteResponseDto);
        });

        return responseDtoList;

    }

    @GetMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto getById(@PathVariable Long idCliente) {

        ClienteModel clienteModel = clienteService.getById(idCliente);

        ClienteResponseDto clienteResponseDto = new ClienteResponseDto();
        clienteResponseDto.setId(clienteModel.getId());
        clienteResponseDto.setEmail(clienteModel.getEmail());
        clienteResponseDto.setNome(clienteModel.getNome());

        return clienteResponseDto;

    }

    @PutMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteSalvarResponseDto update(@PathVariable Long idCliente, @RequestBody ClienteSalvarRequestDto requestDto) {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(idCliente);
        clienteModel.setNome(requestDto.getNome());
        clienteModel.setEmail(requestDto.getEmail());

        clienteModel = clienteService.salva(clienteModel);

        ClienteSalvarResponseDto responseDto = new ClienteSalvarResponseDto();
        responseDto.setId(clienteModel.getId());
        responseDto.setNome(clienteModel.getNome());
        responseDto.setEmail(clienteModel.getEmail());

        return responseDto;
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long idCliente) {
        clienteService.excluir(idCliente);
    }

}
