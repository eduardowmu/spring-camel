package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet");

        rest("/names")
                .post()
                .to("direct:names-list");

        from("direct:names-list")
                .transform()
                .body(String.class)
                .split(body())
                .delimiter(",")
                .to("direct:print-name");

        from("direct:print-name")
                .to("log:print-name");
    }
}