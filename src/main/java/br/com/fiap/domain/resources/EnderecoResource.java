package br.com.fiap.domain.resources;


import br.com.fiap.domain.entity.Endereco;
import br.com.fiap.domain.service.EnderecoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.Objects;


@Path("/endereco")
public class EnderecoResource {

    //Acionando o método construtor:
    private EnderecoService service =   EnderecoService.build();


    @GET
    @Path("/{cep}")
    public Response findByCep(@PathParam("cep") String cep) {
        Endereco endereco = service.findByCep(cep);
        if (Objects.isNull(endereco)) return Response.status(404).build();
        return Response.ok(endereco).build();
    }

}
