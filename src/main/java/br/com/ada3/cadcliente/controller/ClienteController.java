package br.com.ada3.cadcliente.controller;

import br.com.ada3.cadcliente.dto.request.ClienteSalvarRequestDto;
import br.com.ada3.cadcliente.dto.response.ClienteSalvarResponseDto;
import br.com.ada3.cadcliente.model.ClienteModel;
import br.com.ada3.cadcliente.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listaTodos(Model model) {
        List<ClienteModel> clientes = clienteService.listarTodos();
        clientes.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
        model.addAttribute("clientes", clientes);
        return "cliente-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new ClienteSalvarRequestDto());
        return "cliente-edit";
    }

    @PostMapping("/salva")
    public String salva(@ModelAttribute ClienteSalvarResponseDto clienteDto, BindingResult errors) {

        if (errors.hasErrors()) {
            return "cliente-edit";
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());

        clienteService.salva(cliente);

        return "redirect:/cliente";
    }

}
