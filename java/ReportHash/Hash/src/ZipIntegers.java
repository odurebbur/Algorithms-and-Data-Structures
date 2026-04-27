import java.io.BufferedReader;
import java.io.FileReader;

public class ZipIntegers {

    Node[] data;
    int max = 10000;

    private class Node {
        Integer code;
        String name;
        Integer population;
        Node next;

        public Node(Integer code, String name, Integer population){
            this.code = code;
            this.name = name;
            this.population = population;
            next = null;
        }
    }

    public ZipIntegers(String file) {
        data = new Node[10000];
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

    private String binary(Integer zip){
        int min = 0;
        int mx = max;
        while(min <= mx){
            int index = (min + mx)/2;

            if (data[index].code.equals(zip)) {
                return data[index].name;
            }
            if(data[index].code > zip){
                mx = index - 1;
            }
            if(data[index].code < zip){
                min = index + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ZipIntegers file = new ZipIntegers("postnummer.csv");

        //System.out.println(file.binary(11115));

        int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){

            long t0 = System.nanoTime();

            file.binary(98499);

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