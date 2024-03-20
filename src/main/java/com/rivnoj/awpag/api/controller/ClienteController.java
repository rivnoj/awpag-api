package com.rivnoj.awpag.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivnoj.awpag.domain.model.Cliente;

@RestController
public class ClienteController {
  @GetMapping("/clientes")
  public List<Cliente> listar() {
    Cliente cliente1 = new Cliente();
    cliente1.setId(1L);
    cliente1.setNome("João");
    cliente1.setTelefone("81 97654-3876");
    cliente1.setEmail("joaodascouves@email.com");

    Cliente cliente2 = new Cliente();
    cliente2.setId(2L);
    cliente2.setNome("Marcelo");
    cliente2.setTelefone("81 93444-3476");
    cliente2.setEmail("marcelosilva@email.com");

    return Arrays.asList(cliente1, cliente2);
  }
}
