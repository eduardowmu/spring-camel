package org.acme.camel.route;

import org.acme.camel.process.FileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;
import org.apache.camel.support.builder.Namespaces;

import static org.apache.camel.component.file.FileConstants.FILE_NAME;

public class IntegrationFile extends RouteBuilder {
    private FileProcessor fileProcessor = new FileProcessor();

    @Override
    public void configure() throws Exception {
        var ns = new Namespaces("ns", "http://www.portalfiscal.inf.br/nfe");
        from("file:{{diretorioEntrada}}?delay=5000")
                .routeId("integration-file")
                //chamando uma processor
                /*.process(exchange -> System.out.println(
                exchange.getMessage().getBody(String.class)))*/
                .process(exchange -> this.fileProcessor.process(exchange))
                .log("Processando arquivo: ${file:name}")
                .setProperty("CNPJ", xpath("{{xpathCnpjTransportadora}}", ns))
                //NOMEANDO O ARQUIVO de saida
                //.setHeader(FILE_NAME, simple("${date:now:HHmmss}_${file:name}"))
                                                //ou podemos nomear dessa maneira
                //.to("file:{{diretorioSaida}}?fileName=${date:now:HHmmss}_${file:name}");
                .choice()
                    .when(//xpath("{{xpathCnpjTransportadora}}")
                            exchangeProperty("CNPJ").isEqualTo("1"))
                    .to("file:{{diretorioTransportadora}}?fileName=${date:now:HHmmss}_${file:name}")
                    .when(//xpath("{{xpathCnpjTransportadora}}")
                            exchangeProperty("CNPJ").isEqualTo("2"))
                                                        //a cada 5 em 5s permitirá requisição
                        .throttle(1).timePeriodMillis(5000).asyncDelayed()
                            //.log("HTTP")
                            .setHeader(HttpConstants.HTTP_METHOD, constant("POST"))
                            .setHeader(HttpConstants.HTTP_URI, constant("{{urlApiTransportadora2}}"))
                            .setHeader(HttpConstants.HTTP_PATH, constant("nfes"))
                            .setHeader(HttpConstants.CONTENT_TYPE//, constant("application/xml")
                            //a continuação abaixo tem o mesmo efeito que o de cima
                            ).constant("application/xml")
                            .to("http:servidorTransportadora2")
                .endChoice()
                .otherwise()
                .log("Transportadora não integrada")
                .end();
    }
}