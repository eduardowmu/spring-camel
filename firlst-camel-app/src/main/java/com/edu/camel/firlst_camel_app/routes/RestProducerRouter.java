package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestProducerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet");

        rest("/myCamelApp")
                .get()
                .to("direct:my-camel-app");

        from("direct:my-camel-app")
                .transform()
                .constant("Welcome to my Camel App!");
    }
}
