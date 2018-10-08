package org.nirmal.buddy.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author nirmal.s
 * Queue always denies offer for insertion after queue tolerate count
 *
 */
public class DenyingQueue<E> extends LinkedBlockingQueue<E>{

    /**
     *
     */
    private static final long serialVersionUID = -1571431568375679476L;

    private long tolerateQueueSize;


    public DenyingQueue(int tolerateQueueSize) {
        this.tolerateQueueSize = tolerateQueueSize;
    }
    public DenyingQueue() {
        this(1); // default tolerate size = 1
    }

    @Override
    public boolean offer(E e) {
        return (size()<tolerateQueueSize)? super.offer(e):false;
    }
    public long getTolerateQueueSize() {
        return tolerateQueueSize;
    }
    public void setTolerateQueueSize(long tolerateQueueSize) {
        this.tolerateQueueSize = tolerateQueueSize;
    }

    public int getOverLoadQueueSize() {
        if(size()>tolerateQueueSize)
            return (int) (size()-tolerateQueueSize);
        else
            return 0;
    }

}
