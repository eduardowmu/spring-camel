package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test-kafka?period=10000")
                .setBody(simple("Welcome to kafka test"))
                .to("kafka:myKafka");
    }
}
