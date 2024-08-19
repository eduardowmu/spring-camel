package org.acme.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;

public class IntegracaoTransportadora2 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integracaoTransportadora2")
                .routeId("integration-file-transporter2")
                .throttle(1).timePeriodMillis(5000).asyncDelayed()
                //.log("HTTP")
                .setHeader(HttpConstants.HTTP_METHOD, constant("POST"))
                .setHeader(HttpConstants.HTTP_URI, constant("{{urlApiTransportadora2}}"))
                .setHeader(HttpConstants.HTTP_PATH, constant("nfes"))
                .setHeader(HttpConstants.CONTENT_TYPE//, constant("application/xml")
                        //a continuação abaixo tem o mesmo efeito que o de cima
                ).constant("application/xml")
                .to("http:servidorTransportadora2");
    }
}
