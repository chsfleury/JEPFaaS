package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try(Jep jep = new Jep()) {
            jep.eval("from java.lang import System");
            jep.eval("s = 'Hello World'");
            jep.eval("System.out.println(s)");
            jep.eval("print(s)");
            jep.eval("print(s[1:-1])");
        } catch (JepException e) {
            e.printStackTrace();
        }
    }

}
