package com.rivnoj.awpag.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelamentoInput {
  @NotBlank
  @Size(max = 20)
  private String descricao;

  @NotNull
  @Positive
  private BigDecimal valorTotal;

  @NotNull
  @Positive
  @Max(12)
  private Integer quantidadeParcelas;

  @Valid
  @NotNull
  private ClienteIdInput cliente;
}
