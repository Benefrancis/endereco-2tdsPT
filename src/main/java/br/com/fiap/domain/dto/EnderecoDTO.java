package br.com.fiap.domain.dto;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Endereco;
import br.com.fiap.domain.service.EnderecoService;
import br.com.fiap.infra.security.entity.Pessoa;

import java.util.Objects;

public record EnderecoDTO(PessoaDTO pessoa, String cep, String numero, String complemento) {

    private static EnderecoService service = EnderecoService.build( Main.PERSISTENCE_UNIT);

    public static EnderecoDTO of(Endereco endereco) {
        PessoaDTO pessoa = PessoaDTO.of( endereco.getPessoa() );
        return new EnderecoDTO( pessoa, endereco.getCep(), endereco.getNumero(), endereco.getComplemento() );
    }

    public static Endereco of(EnderecoDTO dto) {
        Endereco endereco = service.findByCep( dto.cep() );
        if (Objects.isNull( endereco )) return null;
        endereco.setNumero( dto.numero() );
        endereco.setComplemento( dto.complemento() );
        Pessoa pessoa = PessoaDTO.of( dto.pessoa() );
        endereco.setPessoa( pessoa );
        return endereco;
    }

}
