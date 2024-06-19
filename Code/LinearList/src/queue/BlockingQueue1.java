package queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements BlockingQueue<E> {
    private E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    //锁对象
    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();//配合poll方法
    private Condition tailWaits = lock.newCondition();//配合offer方法

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                //满了就等待
                tailWaits.await();
            }
            array[tail] = e;
            tail = (tail + 1 == array.length ? 0 : tail + 1);
            size++;
            //唤醒等待元素非空的线程
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @param e 待添加的元素
     * @param time 等待的最长时间，单位是毫秒
     * @return true表示添加成功
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long time) throws InterruptedException {
        lock.lockInterruptibly();
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
            size++;
            //唤醒等待元素非空的线程
            headWaits.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        E e = null;
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;//help GC
            head = (head + 1 == array.length ? 0 : head + 1);
            size--;
            tailWaits.signal();//唤醒等待元素非满的线程
        } finally {
            lock.unlock();
        }
        return e;
    }

    private boolean isFull() {
        return size == array.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = head;
        int count = 0;
        while (count != size) {
            s.append(array[i]).append("  ");
            i = (1 + i) % array.length;
            count++;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        Thread t1 = new Thread(() -> {
            try {
                long start = System.currentTimeMillis();
                System.out.println("begin");
                queue.offer("任务1");
                System.out.println(queue);
                queue.offer("任务2");
                System.out.println(queue);
                queue.offer("任务3");
                System.out.println(queue);
                queue.offer("任务4",5000);
                System.out.println(queue);
                System.out.println("end");
                long end = System.currentTimeMillis();
                System.out.println("共耗时：" + (end-start) + "ms");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1");
        t1.start();

        try {
            Thread.sleep(2000);
            queue.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
