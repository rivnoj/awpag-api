package com.rivnoj.awpag.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivnoj.awpag.domain.exception.NegocioException;
import com.rivnoj.awpag.domain.model.Cliente;
import com.rivnoj.awpag.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClienteService {

  private final ClienteRepository clienteRepository;

  @SuppressWarnings("null")
  public Cliente buscar(Long clienteId) {
    return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
  }

  @Transactional
  public Cliente salvar(Cliente cliente) {
    boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                                            .filter(c -> !c.equals(cliente))
                                            .isPresent();

    if (emailEmUso) {
      throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
    }

    return clienteRepository.save(cliente);
  }

  @SuppressWarnings("null")
  @Transactional
  public void excluir(Long clienteId) {
    clienteRepository.deleteById(clienteId);
  }
}
