package br.com.fiap.domain.dto;

import br.com.fiap.Main;
import br.com.fiap.infra.security.entity.Pessoa;
import br.com.fiap.infra.security.entity.PessoaFisica;
import br.com.fiap.infra.security.service.PessoaFisicaService;
import br.com.fiap.infra.security.service.PessoaJuridicaService;

import java.time.LocalDate;
import java.util.Objects;

public record PessoaDTO(Long id, String nome, String email, LocalDate nascimento, String tipo) {

    static PessoaFisicaService pfService = PessoaFisicaService.of( Main.PERSISTENCE_UNIT );
    static PessoaJuridicaService pjService = PessoaJuridicaService.of( Main.PERSISTENCE_UNIT );

    public static Pessoa of(PessoaDTO dto) {
        Pessoa pessoa = null;
        if (dto == null || dto.id().equals( null )) return null;

        pessoa = pfService.findById( dto.id() );

        if (Objects.isNull( pessoa )) {
            pjService.findById( dto.id() );
        }
        return pessoa;
    }


    public static PessoaDTO of(Pessoa pessoa) {

        if (pessoa == null || pessoa.getId().equals( null )) return null;

        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getEmail(),
                pessoa.getNascimento(),
                pessoa instanceof PessoaFisica ? "PF" : "PJ"
        );
    }


}
