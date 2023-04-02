package com.dtp.core.notify.thred;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ruoan
 * @since 1.1.2
 */
public class CapturedBlockingQueue extends AbstractQueue<Runnable> implements BlockingQueue<Runnable> {

    private final String simpleName;

    private final int size;

    private final int remainingCapacity;

    public CapturedBlockingQueue(BlockingQueue<Runnable> blockingQueue) {
        this.simpleName = blockingQueue.getClass().getSimpleName();
        this.size = blockingQueue.size();
        this.remainingCapacity = blockingQueue.remainingCapacity();
    }

    public String getSimpleName() {
        return simpleName;
    }

    @Override
    public Iterator<Runnable> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Runnable runnable) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(Runnable runnable, long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Runnable take() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Runnable poll(long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remainingCapacity() {
        return remainingCapacity;
    }

    @Override
    public int drainTo(Collection<? super Runnable> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int drainTo(Collection<? super Runnable> c, int maxElements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(Runnable runnable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Runnable poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Runnable peek() {
        throw new UnsupportedOperationException();
    }

}