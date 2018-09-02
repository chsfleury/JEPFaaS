package fr.chsfleury.jepfaas;

import java.util.concurrent.ThreadFactory;

/**
 * @author Charles Fleury
 * @since 02/09/18.
 */
public class JepThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(final Runnable r) {
        return new JepThread(r);
    }

}
