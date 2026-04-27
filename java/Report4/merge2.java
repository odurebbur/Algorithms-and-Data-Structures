import java.util.Arrays;
import java.util.Random;

public class merge2 {

    // generate an unsorted array 
    private static int[] unsorted(int n){
        Random rnd = new Random();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = rnd.nextInt(10);
        }
        return array;
    }

    public static void sort(int[] org){
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        for(int i = 0; i < org.length; i++){
            aux[i] = org[i];
        }
        sort(org, aux, 0, org.length -1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi){
        if (lo != hi){
            int mid = (lo + hi)/2;
            sort(aux, org, lo, mid);
            sort(aux, org, mid+1, hi);

            //merge the two sections using additional array 
            merge(org, aux, lo, mid, hi);
        }
        return;
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi){
        int i = lo; 
        int j = mid+1;

        for(int k = lo; k <= hi; k++){
            if(i > mid){
                org[k] = aux[j++];
            } else if(j > hi){
                org[k] = aux[i++];
            } else if (aux[i] <= aux[j]){
                org[k] = aux[i++];
            }else {
                org[k] = aux[j++];
            }
        }
    }
    
    
    public static void main(String[] args){
        //int[] array = unsorted(10);
        //for (int i = 0; i < array.length; ++i)
            //System.out.println(array[i]);

        int rounds = 1000;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

         for (int i = 0; i < rounds; i++){
            int[] array = unsorted(10000);
    
            long t0 = System.nanoTime();

            sort(array);

            long t1 = System.nanoTime();
            long elapsed_time = t1 - t0;
            if (elapsed_time > max) max = elapsed_time;
            if (elapsed_time < min) min = elapsed_time;
            total += elapsed_time;
        }
        System.out.println(" avg: " + ((double) total)/rounds);
        System.out.println(" min: " + ((double) min));
        System.out.println(" max: " + ((double) max));   


      //sort(array); 
       //System.out.println();
       //for (int i = 0; i < array.length; ++i)
            //System.out.println(array[i]);
    }
    
}