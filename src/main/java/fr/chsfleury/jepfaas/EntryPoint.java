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

        port(8081);
        post("/", (request, response) -> {
            String evalLine = null;
            try {
                Jep jep = new Jep();
//                jep.set("requestBody", request.body());
                evalLine = "requestBody = \"" + request.body() + "\"";
                jep.eval(evalLine);
                for (String line : pythonScriptLines) {
                    evalLine = line;
                    jep.eval(evalLine);
                }
                Object responseBody = jep.getValue("responseBody");
                jep.close();
                return responseBody;
            } catch (JepException e) {
                log.error("error at line: {}", evalLine, e);
                return "error at line: '" + evalLine + "' => " + e.getMessage();
            }
        });
    }

}
