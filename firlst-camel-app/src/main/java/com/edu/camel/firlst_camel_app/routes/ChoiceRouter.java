package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ChoiceRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet");

        rest("/api")
                .post()
                .to("direct:choice-testing");

        from("direct:choice-testing")
                .transform().body(String.class)
                .choice()
                    .when(body().contains("Hello World"))
                        .to("direct:hello-world")
                    .when(simple("${body} contains 'techbuzzblogs'"))
                        .to("direct:techbuzzblogs-route")
                    .when(header("developer").isEqualTo("java"))
                        .to("direct:java-route")
                .otherwise()
                    .to("direct:dummy-route")
                .end();

        from("direct:hello-world")
                .log("Hello World!!!");

        from("direct:techbuzzblogs-route")
                .log("techbuzzblogs!!!");

        from("direct:java-route")
                .log("Java!!!");

        from("direct:dummy-route")
                .log("Dummy!!!");
    }
}
