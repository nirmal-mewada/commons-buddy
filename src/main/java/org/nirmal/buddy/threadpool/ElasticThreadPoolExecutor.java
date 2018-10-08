
package org.nirmal.buddy.threadpool;

import java.lang.management.ManagementFactory;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A custom pool executor extending the Java concurrent ThreadPoolExecutor. It
 * maintains the max and threshold counts for jobs buffered in the queue of the
 * executor.
 * @author nirmal
 */
@SuppressWarnings("rawtypes")
public class ElasticThreadPoolExecutor extends ThreadPoolExecutor implements RejectedExecutionHandler, ElasticThreadPoolExecutorMBean {

    Logger logger = LoggerFactory.getLogger(getClass());

    private boolean registremBean = true;

    private String mBeanName = "com.santafe.service.pools";

    private String poolName = "ElasticThreadPoolExecutor";


    private DenyingQueue<Runnable> queue;

    // @PostConstruct
    public void init() {
        if(registremBean){
            try {
                MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
                ObjectName name = new ObjectName(mBeanName+":type="+poolName);
                mbs.registerMBean(this, name);
            } catch (Exception e) {
                logger.error("Error while registering mbean pool: {}",poolName,e);
            }
        }
        if(getQueue() instanceof DenyingQueue)
            queue = (DenyingQueue<Runnable>) getQueue();
    }



    /**
     * Instantiates a new custom thread pool executor.
     *
     * @param corePoolSize the core pool size
     * @param maxPoolSize the max pool size
     * @param keepAliveTime the keep alive time
     * @param name the name
     */
    public ElasticThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, String name) {
        super(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new DenyingQueue<Runnable>(), new NamedThreadFactory(name));
        this.setRejectedExecutionHandler(this);
        this.poolName = name;
    }

    /**
     *
     * @return
     */
    public boolean hasThresholdReached() {
        return getQueueSize()>1; // if thread pool is full
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            if(!isShutdown()){
                this.getQueue().put(r);
            }else{
                logger.error("Can not add job in pool, pool is shutting down, job: {}, pool: {}",r,this);
            }
        } catch (Exception e) {
            logger.error("Error while adding job in pool -  job: {}, pool: {}",r,this);
        }
    }

    @Override
    public long getQueueSize() {
        return getQueue().size();
    }



    @Override
    public long getTolerateQueueSize() {
        if(getQueue() instanceof DenyingQueue<?>)
            return ((DenyingQueue)getQueue()).getTolerateQueueSize();
        return -1;
    }

    @Override
    public boolean setTolerateQueueSize(long size) {
        if(getQueue() instanceof DenyingQueue<?>){
            ((DenyingQueue)getQueue()).setTolerateQueueSize(size);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "ElasticThreadPoolExecutor ["
                + "poolName=" + poolName
                + ", QueueSize=" + getQueueSize()
                + ", TolerateQueueSize=" + getTolerateQueueSize()
                + ", pool size=" + getCorePoolSize()+"/"+getMaximumPoolSize()
                + ", active jobs=" + getActiveCount()
                + "]";
    }



    /*
     * if all threads are busy then load will be equal to queue size (ignoring tolerate queue size)
     * otherwise load will be = ( busy/max thread count)
     */
    @Override
    public double getLoad() {
        if(queue!=null) //denying queue specific
            return   (getActiveCount()/(double)getMaximumPoolSize()) +  queue.getOverLoadQueueSize();
        else //fallback
            return  (getActiveCount()/(double)getMaximumPoolSize()) + getQueueSize();
    }

}