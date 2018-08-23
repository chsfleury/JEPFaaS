package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        try {
            log.info("Starting jep");
            Jep jep = new Jep();
            Path p = Paths.get("/data/handler.py");
            try (Stream<String> lines = Files.lines(p)) {
                lines.forEach(line -> eval(jep, line));
            }
        } catch (JepException e) {
            log.error("error", e);
        }
    }

    private static void eval(Jep jep, String line) {
        try {
            log.info("eval {}", line);
            jep.eval(line);
        } catch (JepException e) {
            log.error("error at: {}", line, e);
        }
    }

}
