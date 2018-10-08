package org.nirmal.buddy.threadpool;

import java.util.concurrent.ThreadFactory;
/**
 * Used to identify threads while debug
 * @author nirmal
 *
 */
public class IndexedThreadFactory implements ThreadFactory {

    int index = 0;
    private String prefix = null;

    public IndexedThreadFactory(String prefixName){
        if(!(prefixName.endsWith("-") || prefixName.endsWith("_")))
            prefixName = prefixName+"-";
        this.prefix = prefixName;

    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix+(index)++);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "IndexedThreadFactory [index=" + index + ", "
                + (prefix != null ? "prefix=" + prefix : "") + "]";
    }
}