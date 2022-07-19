import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer uma conexao HTTP e buscas os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Extrair só os dados que interessam(Titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[0m Titulo: \u001b[1m" + filme.get("title"));
            System.out.println("\u001b[0m Poster: \u001b[1m" + filme.get("image"));
            System.out.println("\u001b[37m \u001b[41mClassificação: " + filme.get("imDbRating") + "\u001b[0m");
            
            float a = Float.parseFloat(filme.get("imDbRating"));           
            int ab = (int) a;
            String star = "\u2B50";
            
            for(int i=0; i<ab; i++){
                if(ab <= a){
                    System.out.printf(star);
                }
            }
            if(a > ab + 0.4){
                System.out.printf(star);
            }
            
            System.out.println();
        }
    }
}
