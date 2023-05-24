package com.rodrigo.ProdManager.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AtualizarCategoriaDTO(long id, @NotBlank  @Length(min = 5, max = 20) String nome) {
}
