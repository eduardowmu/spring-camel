package org.acme.camel.route;

import org.apache.camel.builder.RouteBuilder;

public class IntegracaoTransportadora1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integracaoTransportadora1")
                .routeId("integration-file-transporter1")
                .to("file:{{diretorioTransportadora}}?fileName=${date:now:HHmmss}_${file:name}");
    }
}
