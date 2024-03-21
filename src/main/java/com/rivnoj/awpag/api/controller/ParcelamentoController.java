package com.rivnoj.awpag.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rivnoj.awpag.api.model.ParcelamentoModel;
import com.rivnoj.awpag.domain.model.Parcelamento;
import com.rivnoj.awpag.domain.repository.ParcelamentoRepository;
import com.rivnoj.awpag.domain.service.ParcelamentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
  private final ParcelamentoRepository parcelamentoRepository;
  private final ParcelamentoService parcelamentoService;
  
  @GetMapping
  public List<Parcelamento> listar() {
    return parcelamentoRepository.findAll();
  }

  @SuppressWarnings("null")
  @GetMapping("/{parcelamentoId}")
  public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
    return parcelamentoRepository.findById(parcelamentoId)
                                  .map(parcelamento -> {
                                    var parcelamentoModel = new ParcelamentoModel();
                                    parcelamentoModel.setId(parcelamento.getId());
                                    parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
                                    parcelamentoModel.setDescricao(parcelamento.getDescricao());
                                    parcelamentoModel.setDataCriacao(parcelamento.getDataCriacao());
                                    parcelamentoModel.setParcelas(parcelamento.getQuantidadeParcelas());
                                    parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
                                    return ResponseEntity.ok(parcelamentoModel);
                                  })
                                  .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento) {
    return parcelamentoService.cadastrar(parcelamento);
  }
}
