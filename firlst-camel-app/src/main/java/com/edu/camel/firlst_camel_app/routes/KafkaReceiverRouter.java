package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:myKafka")
                .log("${body}");
    }
}
