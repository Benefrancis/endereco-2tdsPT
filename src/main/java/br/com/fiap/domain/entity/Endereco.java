package br.com.fiap.domain.entity;

import br.com.fiap.infra.security.entity.Pessoa;
import jakarta.persistence.*;


@Entity
@Table(name = "TB_ENDERECO", uniqueConstraints = {
        @UniqueConstraint( name = "UK_ENDERECO_PESSOA", columnNames = { "PESSOA", "CEP"})
})
public class Endereco {


    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_TB_ENDERECO_PESSOA"),
            nullable = false
    )
    private Pessoa pessoa;

    @Id
    @Column(name = "CEP", nullable = false)
    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;


    public Endereco() {
    }

    public Endereco(Pessoa pessoa, String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
        this.pessoa = pessoa;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }


    public Pessoa getPessoa() {
        return pessoa;
    }

    public Endereco setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Endereco setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Endereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getLocalidade() {
        return localidade;
    }

    public Endereco setLocalidade(String localidade) {
        this.localidade = localidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public Endereco setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getIbge() {
        return ibge;
    }

    public Endereco setIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public String getGia() {
        return gia;
    }

    public Endereco setGia(String gia) {
        this.gia = gia;
        return this;
    }

    public String getDdd() {
        return ddd;
    }

    public Endereco setDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public String getSiafi() {
        return siafi;
    }

    public Endereco setSiafi(String siafi) {
        this.siafi = siafi;
        return this;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "pessoa=" + pessoa +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}
