package br.edu.camel.file.route;

import br.edu.camel.file.process.FileProcess;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRote extends RouteBuilder {
    private static String PATH_FILE = "file/";

    @Override
    public void configure() throws Exception {
        /*Por default o apache camel cria um arquivo com este nome
        * no path especificado.
        *
        * Se quisermos que esta pasta em seguida
        * seja excluida, basta concatenar "input" com "?delete=true".
        *
        * Se quisermos mover um arquivo do input para outro, basta concatena-lo
        * com "?move=${date:now:yyyy-MM-dd}/${file:name}"
        *
        * E se quisermos que crie um backup, ou seja, um arquivo de copia, so
        * concatemar o "imput" com "?move=${date:now:yyyy-MM-dd}/copy-${file:name}"
        *
        * Quando tentamos adicionar um arquivo ja existente, se concatenarmos
        * "input" com "?noop=false", este parametro nao permitira processar
        * arquivos ja existente
        *
        * Por default, o camel executa processos apenas dentro do diretorio input,
        * mas se quisermos processar arquivos que estao em subpastas, basta concatenar
        * "input" com "?recursive=true"
        *
        * Se quisermos que seja excluidos arquivos com determinadas extensoes, como
        * .png e .txt por exemplo: "?excludeExt=png, txt"
        *
        * "?timeUnit=MINUTES&initialDelay=1" isso gera um atraso para que o camel
        * comece a processar. Ao inves de minutos e quisermos que seja em segundos,
        * basta trocar por SECONDS. Isso caracteriza um schedulle
        *
        * Uma outra forma de processamento com delay, porem com repetiçoes, e
        * "?delay=10000&repeatCount=3", que indica a cada 10 segundos, reprocesse
        * com limite de 3x
        *
        * Podemos tambem definir regra para filtrar arquivo, com, por exemplo
        * "?filterFile=${file:size} < 422" este tamanho representado em BYTES
        *
        * Se quisermos filtrar arquivos, de determinadas extensoes, a serem
        * processadas, concatenamos "input" com "?includeExt=txt" se quisermos
        * que processe apenas arquivos do tipo .txt
        */
        from("file://".concat(PATH_FILE + "input"))
                /*Podemos tambem tomar decisoes a partir da rota*/
                .choice()
                .when(simple("${header.CamelFileLength} < 422"))
                    .to("bean:fileComponent")
                /*Caso contrario, queremos processar*/
                .otherwise()
                    .process(new FileProcess());
            //o Camel ira buscar o objeto, cuja classe tenha este nome
            //.bean("fileComponent")

            /*podemos tambem via processo, instanciado a classe FileProcessor
            * que criamos.*/
            //.process(new FileProcess())
            /*exige que defina um diretorio onde sera enviado o arquivo
            * processado.
            *
            * Se concatenarmos o "output" com "?flatten=true" e concatenarmos
            * "input" acima com "recursive=true&delete=true", o flatten junto
            * com recursive ira considerar os arquivos das subpastas e irao
            * colocar todos os arquivos, seja de subpastas ou nao, dentro de uma
            * mesma pasta no output, e o delete e necessario caso contrario
            * ira entrar em um looping*/
            //.to("file://".concat(PATH_FILE + "output"));
            /*A linha de cima tambem poderia ser escrito da seguinte maneira:
            *.to("bean:fileComponent");*/
    }
}