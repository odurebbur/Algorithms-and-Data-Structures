public class Queue {
    Node head;
    Node tail;
        private class Node {
            Integer item;
            Node next;

            private Node(Integer item, Node list){
                this.item = item;
                this.next = list;
            }
        }
    public Queue(){
        head = null;
        tail = null;
    }

    public void enqueue(Integer item){
        Node newNode = new Node(item, null);
        if (tail == null){
            head = tail = newNode;
            return;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public Integer dequeue(){
        if (head == null || tail == null)
            return null;

        head = head.next;
        return null;
    }


    //create a queue of size n 
    static void queueCreation(Queue queue, int n){
        int i = 0;
        for(i = 0; i < n; i++){
            queue.enqueue(i);
        }
    }
    
    public static void main(String[] args){

        int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){

            Queue queue = new Queue();
            queueCreation(queue, 10);


            long t0 = System.nanoTime();

            queue.dequeue();

            long t1 = System.nanoTime();
            long elapsed_time = t1 - t0;
            if (elapsed_time > max) max = elapsed_time;
            if (elapsed_time < min) min = elapsed_time;
            total += elapsed_time;
        }
        System.out.println(" avg: " + ((double) total)/rounds);
        System.out.println(" min: " + ((double) min));
        System.out.println(" max: " + ((double) max)); 
        
    }
}
