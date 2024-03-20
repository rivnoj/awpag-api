package com.rivnoj.awpag.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivnoj.awpag.domain.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class ClienteController {

  @PersistenceContext
  private EntityManager manager;

  @GetMapping("/clientes")
  public List<Cliente> listar() {
    return manager.createQuery("from Cliente", Cliente.class)
            .getResultList();
  }
}
