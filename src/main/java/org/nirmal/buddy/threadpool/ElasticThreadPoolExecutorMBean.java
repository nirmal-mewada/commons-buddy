package org.nirmal.buddy.threadpool;

/**
 *
 * @author nirmal.s
 *
 */
public interface ElasticThreadPoolExecutorMBean {

    long getTaskCount();
    int getActiveCount();

    public int getCorePoolSize();
    public void setCorePoolSize(int corePoolSize);

    int getPoolSize();

    public int getMaximumPoolSize();
    public void setMaximumPoolSize(int maximumPoolSize);


    public boolean isTerminated();
    public boolean isShutdown();

    long getTolerateQueueSize();
    public boolean setTolerateQueueSize(long size);

    long getQueueSize();

    double getLoad();
}

