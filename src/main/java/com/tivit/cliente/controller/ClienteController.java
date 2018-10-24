package com.tivit.cliente.controller;

import com.tivit.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "index";
    }

}
