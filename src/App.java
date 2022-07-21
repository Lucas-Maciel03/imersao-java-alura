import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer uma conexao HTTP e buscas os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        var extrator = new ExtratorDeConteudoDoImdb();

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        //var extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.bucaDados(url);
        
        //Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i=0; i<3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream  inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());            
            System.out.println();
        }
    }
}
