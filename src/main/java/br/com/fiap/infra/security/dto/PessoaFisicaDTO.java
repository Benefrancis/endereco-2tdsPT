package br.com.fiap.infra.security.dto;


import br.com.fiap.infra.security.entity.PessoaFisica;
import br.com.fiap.infra.security.entity.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PessoaFisicaDTO(
        Long id,
        String nome,
        @PastOrPresent LocalDate nascimento,
        @Email String email,
        String password,
        Sexo sexo,
        String cpf
) {
    public static PessoaFisicaDTO of(PessoaFisica p) {
        return new PessoaFisicaDTO( p.getId(), p.getNome(), p.getNascimento(), p.getEmail(), "", p.getSexo(), p.getCpf() );
    }


    public static PessoaFisica of(PessoaFisicaDTO p) {

        return new PessoaFisica( p.id, p.nome, p.nascimento, p.email, p.password, p.sexo, p.cpf );
    }
}
