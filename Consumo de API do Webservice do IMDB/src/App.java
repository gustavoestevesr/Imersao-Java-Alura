import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Definindo ENDPOINT E KEY
        // String URL = "https://imdb-api.com/en/API/Top250TVs/";
        // String KEY = "k_n6q7rmgq";
        String URL = "https://alura-filmes.herokuapp.com/conteudos";

        // ACESSAR API
        // URI uri = URI.create(URL+KEY);
        URI uri = URI.create(URL);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        // PEGAR RESPONSE (JSON)
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // PARSEAR DADOS DE INTERESSE
        JsonParser parser = new JsonParser();
        List<Map<String, String>> lista_de_filmes = parser.parse(body);

        // MOSTRAR RESPONSE PARSEADO
        for (Map<String, String> filme : lista_de_filmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            float rating = Float.valueOf(filme.get("imDbRating"));
            for (int index = 0; index < rating; index++) {
                System.out.print("@ ");
            }
            System.out.print(rating + "\n\n");

        }

    }
}
