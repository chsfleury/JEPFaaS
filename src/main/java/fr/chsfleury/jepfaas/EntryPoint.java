package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.post;

@Slf4j
public class EntryPoint {

    public static void main(String[] args) throws IOException {
        final Path pythonScript = Paths.get("/data/handler.py");
        final List<String> pythonScriptLines = Files.readAllLines(pythonScript);

        port(8080);
        post("/", (request, response) -> {
            try {
                Jep jep = new Jep();
                jep.set("requestBody", request.body());
                for (String line : pythonScriptLines) {
                    jep.eval(line);
                }
                return jep.getValue("responseBody");
            } catch (JepException e) {
                log.error("error", e);
                return e.getMessage();
            }
        });
    }

}
