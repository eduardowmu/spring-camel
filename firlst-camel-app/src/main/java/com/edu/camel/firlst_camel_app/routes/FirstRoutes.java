package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FirstRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://test?period=5000")
                .setBody(simple("Welcome!!"))
                .to("log:test");
    }
}