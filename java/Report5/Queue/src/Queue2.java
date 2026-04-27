public class Queue2 {
    int front; // track the first element 
    int rear; // track the first empty slot
    int size; // size of the allocated array
    Integer[] queue;
    int count; // track number elements in queue

    //constructor to intialize the queue 
    Queue2(int n){
        front = -1;
        rear = -1;
        size = n;
        queue = new Integer[size];
        count = 0;
    }

    void enqueue(int val){
        if (count == size){ // check if the queue is full 
            queue = grow(queue);
        }
        if(front == -1){ // check if the queue is empty
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % size;
        }
        queue[rear] = val;
        count++;
    }

    public void dequeue(){
        if(count == 0){ //check if the queue is empty
            System.out.println("The queue is empty, cannot remove an element");
            return;
        }
        // remove the front element 
        queue[front] = null;
        front = (front+1) % size;
        count--;
        if(count == 0){ // if the array is empty reset the front and rear values 
            front = -1;
            rear = -1;
        }
    }
     Integer[] grow(Integer[] array){
        Integer[] largerArray = new Integer[array.length*2]; 
        for(int i = 0; i < array.length; i++){
            largerArray[i] = array[(front + i) % array.length];
        }
        front = 0;
        rear = count - 1; 
        size = array.length*2;
        return largerArray;
     }

    public void printQueue(){
        for(int i = 0; i < count; i++){
            System.out.println(queue[(front+i)%size]);
        }
     }

     public static void main(String[] args){
        Queue2 queue = new Queue2(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
    
        queue.enqueue(1);
        queue.dequeue();
       
        queue.printQueue();
     }
}
