package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileTestingRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:{{input}}?delay=1000")
                .log("${body}")
                .to("file:{{output}}");
    }
}
