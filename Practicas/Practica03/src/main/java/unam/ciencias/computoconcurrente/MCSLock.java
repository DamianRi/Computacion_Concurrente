package unam.ciencias.computoconcurrente;

import java.util.concurrent.atomic.AtomicReference;
import java.lang.ThreadLocal;

public class MCSLock implements Lock {

    AtomicReference<QNode> queue;
    ThreadLocal<QNode> myNode;

    public MCSLock() {
        queue = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    @Override
    public void lock() {
        /*
        QNode qnode = myNode.get();
        QNode pred = queue.getAndSet(qnode);
        if (pred != null) {
            qnode.locked = true;
            pred.next = qnode;
            // wait until predecessor gives up the lock
            while (qnode.locked) {
            }
        }
        */
    }

    @Override
    public void unlock() {
        /*
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            if (queue.compareAndSet(qnode, null))
                return;
            // wait until predecessor fills in its next field
            while (qnode.next == null) {
            }
        }
        qnode.next.locked = false;
        qnode.next = null;
        */
    }

    class QNode {
        volatile boolean locked = false;
        QNode next = null;
    }

}
