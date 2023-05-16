package br.edu.camel.debezium.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.annotations.Component;
//import org.springframework.stereotype.Component;

@Component("file/offset-file.dat")
public class DebeziumRoute extends RouteBuilder {
    /*Atributo obrigatorio para que o debizium seja ativado e consiga
    * definir uma rota. Se nao for encontrado, o proprio debizium cria*/
    private static final String OFFSET_STORAGE_FILE_NAME = "file/offset-file.dat";
    private static final String HOST = "localhost";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "12345";
    private static final String DB = "postgres";

    @Override
    public void configure() throws Exception {
        /*"dbz-postgres" e a rota, obrigatoria, para conexao
        * Os dois parametros sao obrigatorios*/
        from("debizium-postgres:dbz-postgres?offsetStorageFileName=" + OFFSET_STORAGE_FILE_NAME
                + "&databaseHostName=" + HOST + "&databaseUser=" + USER_NAME
                + "&databasePassword=" + PASSWORD + "&databaseServerName=" + DB
                + "&databaseDbname=" + DB + "&pluginName=pgoutput")
        .log("EVENTO: ${body}")
        .log("identificador: ${headers.CamelDebeziumIdentifier}")
        .log("source metadata: ${headers.CamelDebeziumSourceMetaData}")
        .log("operaçao: ${headers.CamelDebeziumOperation}")
        .log("base: '${headers.CamelDebeziumSourceMetaData[db]}'")
        .log("tabela: '${headers.CamelDebeziumSourceMetaData[table]}'")
        .log("primary key: ${headers.CamelDebeziumKey}"); //decoderbufs e o padrao do debizium pg
    }
}