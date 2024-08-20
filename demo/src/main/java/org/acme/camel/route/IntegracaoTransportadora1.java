package org.acme.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFileOperationFailedException;

public class IntegracaoTransportadora1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integracaoTransportadora1")
                .routeId("integration-file-transporter1")
                .log("Integrando com a transportadora 1")
                //impedindo tratamento de erro para esta rota
                .errorHandler(noErrorHandler())
                .setBody(constant("Teste"))
                .to("file:{{diretorioTransportadora}}?fileName=${date:now:HHmmss}_${file:name}");
    }
}
