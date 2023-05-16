package br.edu.camel.filewatch.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
/*O FileWatch e um componente que fica vigiando um diretorio
* do sistema. Todas as criaçoes, atualizaçao e deleçao de um
* arquivo sao registrados por isto.*/
@Component
public class FileWatchRoute extends RouteBuilder {
    private static final String FILE_WATCH = "file-watch:";
    private static final String FILE_PATH = "file/input";

    @Override
    public void configure() throws Exception {
        /*Poderiamos complementar com, por exemplo, recursividade: "?recursive=false".
        *
        * Ou entao habilitar apenas alguns eventos, atraves de, por exemplo,
        * "?events=CREATE" que habilitara apenas criaçao de arquivo no diretorio*/
        from(FILE_WATCH + FILE_PATH)
                /*Este log nos retornara que evento aconteceu e em qual arquivo*/
                .log("Evento: ${header.CamelFileEventType} Arquivo: ${header.CamelFileName}");
    }
}
