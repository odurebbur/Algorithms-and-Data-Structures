import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ZipIntegers2 {

    Node[] data;
    int max = 10000;
    int mod = 10000;

    LinkedList<Node>[] hashTable = new LinkedList[mod];

    private class Node {
        Integer code;
        String name;
        Integer population;

        public Node(Integer code, String name, Integer population){
            this.code = code;
            this.name = name;
            this.population = population;
        }
    }

    public ZipIntegers2(String file) {
        data = new Node[max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null && i < this.max) {
            String[] row = line.split(",");
            Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
            data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
        }
        this.max = i;
        } catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public void bucketHash(){
    
        int mod = 10000;
        
        // intitialize each element of hash table to be a linked list 
        for(int i = 0; i < mod; i++){ 
            hashTable[i] = new LinkedList<>(); 
        }

        for (int i = 0; i < max; i++) { 
            if(this.data[i] != null){
                Integer index = (this.data[i].code)%mod; // compute the hash 
                hashTable[index].add(this.data[i]);
            }
        }
    }

    public String lookup(Integer find){
        Integer index = find%mod; // compute the hash 
        for(int i = 0; i < hashTable[index].size(); i++){
            Node node = hashTable[index].get(i);
            if(node.code.equals(find)){
                return node.name;
            }
        }
        return null;
    }

    public void collisions(int mod) {

        int mx = 20;

        int[] table = new int[mod];
        int[] cols = new int[mx];

        for (int i = 0; i < max; i++) {
            if(this.data[i] != null){
                Integer index = (this.data[i].code)%mod;
                table[index]++;
            }
        }
        // count collisions
        int totalCollisions = 0;
        for(int i = 0; i < mod; i++) {
            if (table[i] < mx)
                cols[table[i]]++;
            if(table[i] > 1){
                totalCollisions += table[i] - 1;
            }
        }
        System.out.println(totalCollisions);
            

        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }   
    

    public static void main(String[] args) {
        ZipIntegers2 file = new ZipIntegers2("postnummer.csv");

        file.bucketHash();
        System.out.println(file.lookup(12050));

        
        //System.out.println(file.data[0].name);

        // int rounds = 1000;

        // long min = Long.MAX_VALUE;
        // long max = 0;
        // long total = 0;

        // for (int i = 0; i < rounds; i++){

        //     long t0 = System.nanoTime();

        //     file.lookup(11115);

        //     long t1 = System.nanoTime();
        //     long elapsed_time = t1 - t0;
        //     if (elapsed_time > max) max = elapsed_time;
        //     if (elapsed_time < min) min = elapsed_time;
        //     total += elapsed_time;
        // }
        // System.out.println(" avg: " + ((double) total)/rounds);
        // System.out.println(" min: " + ((double) min));
        // System.out.println(" max: " + ((double) max)); 
        
    }

}