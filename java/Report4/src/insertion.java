import java.util.Arrays;
import java.util.Random;

public class insertion {

    // generate an unsorted array 
    private static int[] unsorted(int n){
        Random rnd = new Random();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = rnd.nextInt(10);
        }
        return array;
    }

    // generates a sorted array 
    private static int[] sorted(int n){
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n; i++){
            nxt += rnd.nextInt(10) + 1 ;
            array[i] = nxt;
        }
        return array;
    }

    //swaps two elements in an array 
    public static void swap(int[] array, int pos1, int pos2){
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    public static void insertionsort(int[] array){
        for (int i = 0; i < array.length; i++){
            for (int j = i; j > 0 && array[j] < array[j-1]; j--){
                swap(array, j, j-1);
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
            int[] array = sorted(1000);
    
            long t0 = System.nanoTime();

            insertionsort(array);

            long t1 = System.nanoTime();
            long elapsed_time = t1 - t0;
            if (elapsed_time > max) max = elapsed_time;
            if (elapsed_time < min) min = elapsed_time;
            total += elapsed_time;
        }
        System.out.println(" avg: " + ((double) total)/rounds);
        System.out.println(" min: " + ((double) min));
        System.out.println(" max: " + ((double) max));   


       // insertionsort(array); 
        //System.out.println();
      // for (int i = 0; i < array.length; ++i)
          //  System.out.println(array[i]);
    }
    
}
