import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ZipIntegers3 {

    Node[] data;
    int max = 10000;
    int mod = 20000;

    int[] hashTable = new int[mod];

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

    public ZipIntegers3(String file) {
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

    public void linearProbe(){
        int filledIndexes = 0;
        for (int i = 0; i < max; i++) { 
            if(this.data[i] != null){
                Integer index = (this.data[i].code)%mod; // compute the hash 
                while(hashTable[index] != 0){
                    index = (index + 1) % mod; // ensures we wrap around the array at the end 
                }
                hashTable[index] = this.data[i].code;
                filledIndexes++ ;
            }
        }
    }

    public int lookup(Integer find){
        Integer index = find%mod; // compute the hash 
        int probes = 0;
        
        while(hashTable[index] != 0){
            probes++;
            if(hashTable[index] == find){
                return probes;
            }
            index = (index+1) % mod;
        }
        return probes;
    } 
    
    public void testLookupStatistics() {
        int totalProbes = 0;
        int successfulLookups = 0;

        // Lookup each element in the data and collect stats
        for (int i = 0; i < max; i++) {
            if (data[i] != null) {
                int probes = lookup(data[i].code);  // Perform the lookup and get probe count
                totalProbes += probes;
                successfulLookups++;
            }
        }

        if (successfulLookups > 0) {
            System.out.println("Average probes per successful lookup: " + (double) totalProbes / successfulLookups);
        } else {
            System.out.println("No successful lookups!");
        }
    }

    public static void main(String[] args) {
        ZipIntegers3 file = new ZipIntegers3("postnummer.csv");

        file.linearProbe();
        file.testLookupStatistics();
        

        
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