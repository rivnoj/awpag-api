package com.rivnoj.awpag.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivnoj.awpag.domain.exception.NegocioException;
import com.rivnoj.awpag.domain.model.Cliente;
import com.rivnoj.awpag.domain.model.Parcelamento;
import com.rivnoj.awpag.domain.repository.ParcelamentoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ParcelamentoService {
  private final ParcelamentoRepository parcelamentoRepository;
  private final CadastroClienteService cadastroClienteService;

  @Transactional
  public Parcelamento cadastrar(Parcelamento novoParcelamento) {
    if (novoParcelamento.getId() != null) {
      throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
    }
    
    Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());
    
    novoParcelamento.setCliente(cliente);
    novoParcelamento.setDataCriacao(LocalDateTime.now());

    return parcelamentoRepository.save(novoParcelamento);
  }
}
