package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.threadPool;

@Slf4j
public class EntryPoint {

    private static final int MAX_THREADS = 10;

    public static void main(String[] args) throws IOException {
        final Path pythonScript = Paths.get("/data/handler.py");
        final List<String> pythonScriptLines = Files.readAllLines(pythonScript);
        final ExecutorService jepExecutor = Executors.newFixedThreadPool(MAX_THREADS, new JepThreadFactory());

        port(8081);
        threadPool(MAX_THREADS, MAX_THREADS, Integer.MAX_VALUE);
        post("/", (request, response) -> supplyAsync(() -> {
            String evalLine = null;
            try {
                JepThread thread = (JepThread) Thread.currentThread();
                Jep jep = thread.getJep();
                if (jep != null) {
                    evalLine = "requestBody = \"" + request.body() + "\"";
                    jep.eval(evalLine);
                    for (String line : pythonScriptLines) {
                        evalLine = line;
                        jep.eval(evalLine);
                    }
                    return (String) jep.getValue("responseBody");
                } else {
                    response.status(500);
                    return "no jep instance";
                }
            } catch (JepException e) {
                log.error("error at line: {}", evalLine, e);
                response.status(500);
                return "error at line: '" + evalLine + "' => " + e.getMessage();
            }
        }, jepExecutor).get());
    }
}
