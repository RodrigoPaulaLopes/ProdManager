package com.rodrigo.ProdManager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteEmailDTO(@NotBlank(message = "Preenchimento obrigadorio") @Email(message = "O campo deve ser email") String email) {
}
