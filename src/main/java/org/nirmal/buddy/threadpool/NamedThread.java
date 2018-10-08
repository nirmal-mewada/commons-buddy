package org.nirmal.buddy.threadpool;

/**
 *
 * @author nirmal.s
 *
 */
public class NamedThread extends Thread {

    public NamedThread(Runnable run, String name){
        super(run,name);
    }
    public NamedThread(String name) {
        super(name);
    }
}
