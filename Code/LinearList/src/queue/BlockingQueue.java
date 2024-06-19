package queue;

public interface BlockingQueue<E> {
    void offer(E e) throws InterruptedException;

    boolean offer(E e,long time) throws InterruptedException;

    E poll() throws InterruptedException;
}
