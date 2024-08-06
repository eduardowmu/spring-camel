package com.edu.camel.firlst_camel_app.routes;

import com.edu.camel.firlst_camel_app.processor.FirstProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test-rest-api?period=10000")
                .log("Rest API calling")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:9092/api/car/")
                .process(new FirstProcessor());
    }
}