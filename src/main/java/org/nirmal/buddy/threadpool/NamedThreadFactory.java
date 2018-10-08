package org.nirmal.buddy.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 *
 * @author nirmal
 *
 */
public class NamedThreadFactory implements ThreadFactory {

    private String name = null;
    public NamedThreadFactory(String name){
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new NamedThread(r, name);
    }

}