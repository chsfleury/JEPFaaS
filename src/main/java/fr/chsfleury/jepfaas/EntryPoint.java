package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.extern.slf4j.Slf4j;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.threadPool;

@Slf4j
public class EntryPoint {

    private static final String PYTHON_SCRIPT = "/data/handler.py";
    private static final int THREADS = 10;
    private static final ThreadLocal<Jep> JEP_INSTANCE = ThreadLocal.withInitial(() -> {
        try {
            Jep jep = new Jep();
            jep.runScript(PYTHON_SCRIPT);
            return jep;
        } catch (JepException e) {
            log.error("cannot create jep instance", e);
            return null;
        }
    });

    public static void main(String[] args) {
        port(8081);
        threadPool(THREADS, THREADS, Integer.MAX_VALUE);
        post("/", (request, response) -> {
            try {
                Jep jep = JEP_INSTANCE.get();
                if (jep != null) {
                    return jep.invoke("handle", request.body());
                } else {
                    response.status(500);
                    return "no jep instance";
                }
            } catch (JepException e) {
                log.error("invoke error", e);
                response.status(500);
                return "invoke error: " + e.getMessage();
            }
        });
    }
}
