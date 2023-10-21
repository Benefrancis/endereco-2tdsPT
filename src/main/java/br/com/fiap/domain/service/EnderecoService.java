package br.com.fiap.domain.service;

import br.com.fiap.domain.dto.EnderecoDTO;
import br.com.fiap.domain.entity.Endereco;
import br.com.fiap.domain.repository.EnderecoRepository;
import br.com.fiap.infra.configuration.datas.LocalDateTypeAdapter;
import br.com.fiap.infra.database.EntityManagerFactoryProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class EnderecoService {

    private static final AtomicReference<EnderecoService> instance = new AtomicReference<>();

    private final EnderecoRepository repo;


    private EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    public static EnderecoService build(String persistenceUnit) {

        EntityManagerFactory factory = EntityManagerFactoryProvider.of( persistenceUnit ).provide();
        EntityManager manager = factory.createEntityManager();
        EnderecoRepository repo = EnderecoRepository.buld( manager );

        instance.compareAndSet( null, new EnderecoService( repo ) );
        return instance.get();
    }

    private final String ENDERECO_API = "https://viacep.com.br/ws/";

    public Endereco findByCep(String cep) {
        Endereco endereco = null;
        URI uri = null;
        HttpResponse<String> response = null;
        try {
            uri = new URI( ENDERECO_API + cep + "/json" );
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder( uri ).GET().build();
            response = client.send( request, HttpResponse.BodyHandlers.ofString() );
            if (response.statusCode() != 200) return null;

            String body = response.body();

//            Gson gson = new GsonBuilder().create();
//
//            endereco = gson.fromJson( body, Endereco.class );

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter( LocalDate.class, new LocalDateTypeAdapter() )
                    .create();

            endereco = gson.fromJson( body, Endereco.class );


        } catch (URISyntaxException e) {
            throw new RuntimeException( e );
        } catch (IOException e) {
            throw new RuntimeException( e );
        } catch (InterruptedException e) {
            throw new RuntimeException( e );
        }
        return endereco;
    }

    public Endereco persist(EnderecoDTO dto) {

        Endereco endereco = EnderecoDTO.of( dto );

        if (Objects.isNull( endereco ) || Objects.isNull( endereco.getPessoa() )) return null;

        return repo.persist( endereco );

    }
}
