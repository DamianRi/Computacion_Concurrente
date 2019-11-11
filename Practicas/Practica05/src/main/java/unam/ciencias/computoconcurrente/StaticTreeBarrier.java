package unam.ciencias.computoconcurrente;

import java.util.concurrent.atomic.AtomicInteger;

public class StaticTreeBarrier implements Barrier {

    private class Node {

        AtomicInteger childCount;
        Node parent;
        int children;

        public Node(Node myParent,int count){
            this.children = count;
            this.childCount = new AtomicInteger(count);
            this.parent = myParent;
        }

        public void await(){
            boolean mySense = threadSense.get();
             while(childCount.get() > 0){};

            childCount.set(children);

            if(parent !=null){
                parent.childDone();

                while(sense != mySense){};
            }else{
                sense = !sense;
            }
            threadSense.set(!mySense);
        }

        public void childDone() {
            childCount.getAndDecrement();
        }

    }


    volatile int radix;
    volatile int nodes;
    Node[] node;
    volatile boolean sense;
    ThreadLocal<Boolean> threadSense;

    public StaticTreeBarrier(int size, int myRadix) {

        this.radix = myRadix;
        this.node = new Node[size];
        int depth = 0;

        while(size > 1){
            depth++;
            size = size / radix;
        }

        build(null, depth);
        this.threadSense =new ThreadLocal<Boolean>(){
          @Override
            protected Boolean initialValue(){
                return !sense;
            };
        };
    }

    public void build(Node parent,int depth) {
        if (nodes == node.length) {
            parent.childCount.getAndDecrement();
            parent.children--;
            return ;
        }

        if(depth == 0){
            System.out.println("Profundidad cero");
            node[nodes++] = new Node(parent, 0);

        }else{
            Node myNode = new Node(parent, radix);
            System.out.println("Profundidad"+depth);
            node[nodes++] = myNode;
            for(int i = 0; i < radix; i++){

                build(myNode, depth - 1);
            }
        }
    }

    @Override
    public void await() {
        String ID = Thread.currentThread().getName();
        int idhilo = Integer.valueOf(ID);
        node[idhilo].await();
    }


}
