package queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2<E> implements BlockingQueue<E> {
    private E[] array;
    private int head;
    private int tail;
    //private int size;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    //锁对象
    private ReentrantLock headlock = new ReentrantLock();
    private Condition headWaits = headlock.newCondition();//配合poll方法
    private ReentrantLock taillock = new ReentrantLock();
    private Condition tailWaits = taillock.newCondition();//配合offer方法

    @Override
    public void offer(E e) throws InterruptedException {
        taillock.lockInterruptibly();
        int c;
        try {
            while (isFull()) {
                //满了就等待
                tailWaits.await();
            }
            array[tail] = e;
            tail = (tail + 1 == array.length ? 0 : tail + 1);
            c = size.getAndIncrement();//自增
            //如果此次添加之后队列还未满，则唤醒一个offer线程
            if (c + 1 < array.length) {
                tailWaits.signal();
            }
        } finally {
            taillock.unlock();
        }

        //唤醒等待元素非空的线程(只唤醒一个poll线程)
        if (c == 0) {
            headlock.lockInterruptibly();
            try {
                headWaits.signal();
            } finally {
                headlock.unlock();
            }
        }
    }

    /**
     * @param e    待添加的元素
     * @param time 等待的最长时间，单位是毫秒
     * @return true表示添加成功
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long time) throws InterruptedException {
        taillock.lockInterruptibly();
        int c;
        try {
            long nanos = TimeUnit.MILLISECONDS.toNanos(time);//将毫秒转换成纳秒
            while (isFull()) {
                if (nanos <= 0)
                    return false;
                //满了就等待
                nanos = tailWaits.awaitNanos(nanos);//最多等待多少纳秒 返回值代表剩余时间
            }
            array[tail] = e;
            tail = (tail + 1 == array.length ? 0 : tail + 1);
            c = size.getAndIncrement();//自增
            //如果此次添加之后队列还未满，则唤醒一个offer线程
            if (c + 1 < array.length) {
                tailWaits.signal();
            }
        } finally {
            taillock.unlock();
        }

        //唤醒等待元素非空的线程(只唤醒一个poll线程)
        if (c == 0) {
            headlock.lockInterruptibly();
            try {
                headWaits.signal();
            } finally {
                headlock.unlock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        E e = null;
        int c;
        headlock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;//help GC
            head = (head + 1 == array.length ? 0 : head + 1);
            c = size.getAndDecrement();
            //如果队列中还有元素，唤醒一个poll线程
            if (c > 1){
                headWaits.signal();
            }
        } finally {
            headlock.unlock();
        }

        //唤醒等待offer的线程(只唤醒一个)
        if (c == array.length) {
            taillock.lockInterruptibly();
            try {
                tailWaits.signal();
            } finally {
                taillock.unlock();
            }
        }
        return e;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = head;
        int count = 0;
        while (count != size.get()) {
            s.append(array[i]).append("  ");
            i = (1 + i) % array.length;
            count++;
        }
        return s.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> queue = new BlockingQueue2<>(3);
        queue.offer("任务1");
        System.out.println(queue);
        Thread t1 = new Thread(() -> {
            try {
                queue.offer("任务2");
                System.out.println(queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2");
        t2.start();
    }
}
