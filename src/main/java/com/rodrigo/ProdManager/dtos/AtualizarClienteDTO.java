package com.rodrigo.ProdManager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Set;

public record AtualizarClienteDTO(@NotNull(message = "Não deve ser vazio ou null") Long id, @NotBlank(message = "Campo não pode estar vazio") String nome, @NotBlank(message = "Campo não pode estar vazio") @Email(message = "Campo deve ser email") String email, @NotBlank String senha ,@NotBlank(message = "Campo não pode estar vazio")  @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF ou CNPJ inválido") String cpfOuCnpj, @NotNull Integer tipo) {
}
