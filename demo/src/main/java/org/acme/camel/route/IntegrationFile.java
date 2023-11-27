package org.acme.camel.route;

import org.acme.camel.process.FileProcessor;
import org.apache.camel.builder.RouteBuilder;

import static org.apache.camel.component.file.FileConstants.FILE_NAME;

public class IntegrationFile extends RouteBuilder {
    private FileProcessor fileProcessor = new FileProcessor();

    @Override
    public void configure() throws Exception {
        from("file:{{diretorioEntrada}}?delay=5000")
                .routeId("integration-file")
                //chamando uma processor
                /*.process(exchange -> System.out.println(
                exchange.getMessage().getBody(String.class)))*/
                .process(exchange -> this.fileProcessor.process(exchange))
                .log("Processando arquivo: ${file:name}")
                //NOMEANDO O ARQUIVO de saida
                //.setHeader(FILE_NAME, simple("${date:now:HHmmss}_${file:name}"))
                                                //ou podemos nomear dessa maneira
                .to("file:{{diretorioSaida}}?fileName=${date:now:HHmmss}_${file:name}");
    }
}