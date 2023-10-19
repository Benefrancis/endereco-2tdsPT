package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnderecoService {


    private final String ENDERECO_API = "https://viacep.com.br/ws/";

    public Endereco findByCep(String cep) {
        Endereco endereco = null;
        URI uri = null;
        HttpResponse<String> response = null;
        try {
            uri = new URI(ENDERECO_API + cep + "/json");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) return null;

            String body = response.body();
            Gson gson = new GsonBuilder().create();
            endereco = gson.fromJson(body, Endereco.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return endereco;
    }
}
