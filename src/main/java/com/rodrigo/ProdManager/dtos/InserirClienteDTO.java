package com.rodrigo.ProdManager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InserirClienteDTO(
        @NotBlank(message = "Campo não pode estar vazio")
        String nome,
        @NotBlank(message = "Campo não pode estar vazio")
        @Email(message = "Campo deve ser email")
        String email,
        @NotBlank(message = "Campo não pode estar vazio") @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF ou CNPJ inválido")
        String cpfOuCnpj,

        @NotBlank
        String senha,
        @NotNull
        Integer tipo,
        @NotBlank(message = "Campo não pode estar vazio")
        String logradouro,
        @NotBlank(message = "Campo não pode estar vazio")
        String numero,
        @NotBlank(message = "Campo não pode estar vazio")
        String complemento,
        @NotBlank(message = "Campo não pode estar vazio")
        String bairro,
        @NotBlank(message = "Campo não pode estar vazio")
        String cep,

        String telefone1,
        String telefone2,
        @NotNull
        Integer cidadeId,

         @NotNull Integer estadoId
) {
}
