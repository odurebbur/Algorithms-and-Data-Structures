import java.util.Random;

public class Trees {

    private class Node {

        private Integer value;
        private Node left, right;

        private Node(Integer value){
            this.value = value;
            this.left = this. right = null;

        }
        public void add(Integer value){
            if (value == this.value) {
                return ; // do nothing for duplicates
            } else if (value < this.value){ //add smaller values to the left 
                if(this.left == null){
                    this.left = new Node(value);
                } else {
                    this.left.add(value);
                }
            } else { // add larger values to the right
                if(this.right == null){
                    this.right = new Node(value);
                } else {
                    this.right.add(value);
                }
            }
        }

        boolean lookup(Integer key){
            if(this.value == key){
                return true;
            } else if (this.value < key){
                return right != null && right.lookup(key);
            } else {
                return left != null && left.lookup(key);
            }
        }
    }

    public class Queue {
        private QNode head;
        private QNode tail;

            private class QNode {
                Node treeNode;
                QNode next;
    
                private QNode(Node treeNode){
                    this.treeNode = treeNode;
                    this.next = null;
                }
            }

        public Queue(){
            head = null;
            tail = null;
        }
    
        public void enqueue(Node treeNode){
            QNode newNode = new QNode(treeNode);
            if (tail == null){
                head = tail = newNode;
                return;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        public Node dequeue(){
            if (head == null)
                return null;
            
            Node treeNode = head.treeNode;
            head = head.next;

            if(head == null){
                tail = null;
            }
            return treeNode;
        }

        public boolean isEmpty(){
            return head == null;
        }
    }

    public class Sequence{
        private Queue queue;

        public Sequence(){
            queue = new Queue();
            if (root != null){
                queue.enqueue(root);
            }
        }
        public Integer next(){
            if (queue.isEmpty()){
                return null;
            }
            Node cur = queue.dequeue();
            if (cur.left != null){
                queue.enqueue(cur.left);
            }
            if (cur.right != null){
                queue.enqueue(cur.right);
            }
            return cur.value;
        }
    }

    public Sequence sequence(){
        return new Sequence();

    }

    private Node root;

    public Trees(){
        root = null;
    }

    void add(Integer value){
        if (root == null){ //checks if the root is empty 
            root = new Node(value);
        } else {
            root.add(value);
        }
    }

    boolean lookup(Integer key){
        if (root == null){
            System.out.println("The tree is empty!");
            return false;
        } else {
            return root.lookup(key);
        } 
    }

    public void print(){
        if (root == null){
            return;
        }
        Queue queue = new Queue();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            Node cur = queue.dequeue();
            System.out.println(cur.value);
            if(cur.left != null){
                queue.enqueue(cur.left);
            }
            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
    }

    public static void main(String[] args) {

        Trees tree = new Trees();

       /*for (int i = 0; i < sizeOfTree; i++){
            Random rand = new Random();
            Integer value = rand.nextInt(10); // generate random numbers between 0 and 10
            tree.add(value);
        } */

        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(2);
        tree.add(4);
        tree.add(7);
        tree.add(9);

        Trees.Sequence sequence = tree.new Sequence();

        System.out.println(sequence.next());
        System.out.println(sequence.next());
        System.out.println(sequence.next());


    
        
/*  
       int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){
            Random rand = new Random();
            Integer key = rand.nextInt(10000);

            long t0 = System.nanoTime();

            tree.lookup(key);

            long t1 = System.nanoTime();
            long elapsed_time = t1 - t0;
            if (elapsed_time > max) max = elapsed_time;
            if (elapsed_time < min) min = elapsed_time;
            total += elapsed_time;
        }
        System.out.println(" avg: " + ((double) total)/rounds);
        System.out.println(" min: " + ((double) min));
        System.out.println(" max: " + ((double) max)); 
        */


    }
}
