package fr.chsfleury.jepfaas;

import jep.Jep;
import jep.JepException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Charles Fleury
 * @since 02/09/18.
 */
@Slf4j
public class JepThread extends Thread {

    @Getter
    private Jep jep;

    public JepThread(Runnable runnable) {
        super(runnable);
    }

    public Jep getJep() throws JepException {
        if (jep == null) {
            jep = new Jep();
            log.warn("creating jep instance {}", jep);
        }
        return jep;
    }

    @Override
    public void interrupt() {
        if (jep != null) {
            try {
                log.warn("closing jep {}", jep);
                jep.close();
            } catch (JepException e) {
                log.error("cannot close jep", e);
            }
        }
        super.interrupt();
    }
}
