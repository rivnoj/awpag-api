package com.rivnoj.awpag.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rivnoj.awpag.domain.model.Cliente;
import com.rivnoj.awpag.domain.repository.ClienteRepository;
import com.rivnoj.awpag.domain.service.CadastroClienteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

  // @PersistenceContext
  // private EntityManager manager;

  //@Autowired //para teste, não é uma boa prática. Usa-se construtor, ao invés disso.
  //final adicionado depois de usar o lombok, para indicar que, uma vez atribuída, não poderá mais ser alterada
  private final ClienteRepository clienteRepository;

  private final CadastroClienteService cadastroClienteService;

  //Para testar a classe, é uma boa prática criar esse construtor. Mas, com o lombok, não precisa disso.
  // public ClienteController(ClienteRepository clienteRepository) {
  //   this.clienteRepository = clienteRepository;
  // }

  @GetMapping
  public List<Cliente> listar() {
    // return manager.createQuery("from Cliente", Cliente.class)
    //         .getResultList();
    return clienteRepository.findAll();
  }

  @SuppressWarnings("null")
  @GetMapping("/{clienteId}")
  public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
    Optional<Cliente> cliente = clienteRepository.findById(clienteId);

    if (cliente.isPresent()) {
      return ResponseEntity.ok(cliente.get());
    }

    return ResponseEntity.notFound().build();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    return cadastroClienteService.salvar(cliente);
  }

  @SuppressWarnings("null")
  @PutMapping("/{clienteId}")
  public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
                                           @Valid @RequestBody Cliente cliente) {
    if (!clienteRepository.existsById(clienteId)) {
      return ResponseEntity.notFound().build();
    }

    cliente.setId(clienteId);
    cliente = cadastroClienteService.salvar(cliente);
    return ResponseEntity.ok(cliente);
  }

  @SuppressWarnings("null")
  @DeleteMapping("/{clienteId}")
  public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
    if (!clienteRepository.existsById(clienteId)) {
      return ResponseEntity.notFound().build();
    }

    cadastroClienteService.excluir(clienteId);

    return ResponseEntity.noContent().build();
  }
}
