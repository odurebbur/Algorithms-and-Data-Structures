import java.util.Random;

public class trees {

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
    
        public void enqueue(Node node){
            Node newNode = new Node(node.item, null);
            if (tail == null){
                head = tail = newNode;
                return;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        public Node dequeue(){
            if (head == null || tail == null)
                return null;
    
            head = head.next;
            return null;
        }
    }


    private Node root;

    public trees(){
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
        Queue queue = new Queue();
        Node cur = this.root;
        enqueue(cur);
        while(head.queue != null){
            dequeue();
            enqueue(cur.left);
            enqueue(cur.right);
            print();
        }
    }


    public static void main(String[] args) {

        trees tree = new trees();
        int sizeOfTree = 10;

       for (int i = 0; i < sizeOfTree; i++){
            Random rand = new Random();
            Integer value = rand.nextInt(10); // generate random numbers between 0 and 100
            tree.add(value);
        }

        tree.print();
        
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
