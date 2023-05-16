package br.edu.camel.direct.route;

import br.edu.camel.direct.processor.FileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DirectRoute extends RouteBuilder {
    private static final String PATH_FILE = "file/input";

    @Override
    public void configure() throws Exception {
        from("direct:log-file")
                .log("Log: ${header.CamelFileName}")
                .to("direct:log-file2");

        from("direct:log-file2")
                .process(new FileProcessor());

        from("file://".concat(PATH_FILE))
            .to("direct:log-file");
    }
}
