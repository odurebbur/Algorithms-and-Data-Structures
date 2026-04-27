import java.io.BufferedReader;
import java.io.FileReader;

public class TreeZip {

    Node[] data;
    int max = 10000;

    private class Node {
        String code;
        String name;
        Integer population;
        Node next;

        public Node(String code, String name, Integer population){
            this.code = code;
            this.name = name;
            this.population = population;
            next = null;
        }
    }

    public TreeZip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null && i < max) {
            String[] row = line.split(",");
            data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
        }
        this.max = i;
        } catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    private String binary(String zip){
        int min = 0;
        int mx = max;
        while(min <= mx){
            int index = (min + mx)/2;

            int compare = zip.compareTo(data[index].code);
            if (compare == 0) {
                return data[index].name;
            }
            if(compare < 0){
                mx = index - 1;
            }
            if(compare > 0){
                min = index + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TreeZip file = new TreeZip("postnummer.csv");

        //System.out.println(file.binary("984 99"));

       int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){

            long t0 = System.nanoTime();

            file.binary("984 99");

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