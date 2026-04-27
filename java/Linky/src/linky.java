
class linky {
    Cell first;

    private class Cell {
        int head;
        Cell next; // reference to next node

        Cell(int val, Cell t1){
            head = val; 
            next = t1;
        }
    }

    void add(int item){
        first = new Cell(item, first);
    }

    int length(){
        int size = 0;
        Cell current = first;
        while (current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    boolean find(int item){
        Cell current = first;
        while (current != null){
            if (current.head == item){
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    void remove(int item){
        if (first != null && first.head == item){
            first = first.next;
        }
        Cell current = first;
        while (current != null && current.next !=null){
            if (current.head == item){
                current.next = current.next.next;
                return;
            } else {
                current = current.next;
            }
        }
    }

    public void append(linky b){
        Cell nxt = this.first;
        Cell prv = null;
        while(nxt.next != null){
            prv = nxt;
            nxt = nxt.next;
        }
        nxt.next = b.first;
    }
    linky(int n){
        Cell last = null;
        for (int i = 0; i < n; i++){
            last = new Cell(i, last);
        }
        first = last;
    }

    public static void main(String[] args) throws Exception {

       int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){
            linky a = new linky(100);
            linky b = new linky(10000);   

            long t0 = System.nanoTime();

            a.append(b);

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