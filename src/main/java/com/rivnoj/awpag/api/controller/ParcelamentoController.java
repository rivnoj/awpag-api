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
import com.rivnoj.awpag.api.model.input.ParcelamentoInput;
import com.rivnoj.awpag.domain.model.Parcelamento;
import com.rivnoj.awpag.domain.repository.ParcelamentoRepository;
import com.rivnoj.awpag.domain.service.ParcelamentoService;
import com.rivnoj.awpag.api.assembler.ParcelamentoAssembler;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
  private final ParcelamentoRepository parcelamentoRepository;
  private final ParcelamentoService parcelamentoService;
  private final ParcelamentoAssembler parcelamentoAssembler;

  @GetMapping
  public List<ParcelamentoModel> listar() {
    return parcelamentoAssembler.toCollectionModel(parcelamentoRepository.findAll());
  }

  @SuppressWarnings("null")
  @GetMapping("/{parcelamentoId}")
  public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
    return parcelamentoRepository.findById(parcelamentoId)
                                  .map(parcelamentoAssembler::toModel)
                                  .map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInput parcelamentoInput) {
    Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
    Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);
    return parcelamentoAssembler.toModel(parcelamentoCadastrado);
  }
}
