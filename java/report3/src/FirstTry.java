import java.util.Random;
import java.util.Arrays;

public class FirstTry {
    // search through an unsorted array 
    public static long unsorted_search(int[] array, int key){
        long t0 = System.nanoTime();
        for(int index = 0; index < array.length; index++){
            if (array[index] == key){
                break;
            }
        }
        long t1 = System.nanoTime();
        return (t1-t0);

    }
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
    //binary search algorithm 
    public static long binary_search(int[] array, int key){
        long t0 = System.nanoTime();
        int first = 0;
        int last = array.length - 1;
        while (true){
            //jump to middle
            int index = (last + first)/2 ;

            if (array[index] == key){
                long t1 = System.nanoTime();
                //System.out.println("The key exists in the array");
                return (t1-t0);
            }
            if (array[index] < key && index < last){
                first = index + 1;
                continue;
            }
            if (array[index] > key && index > first){
                last = index - 1;
                continue;
            }
            if (index == last && first == last){
                long t1 = System.nanoTime();
                //System.out.println("The key does not exist in the array");
                return (t1-t0);
            }
        }
    }

        private static long recursive(int[] arr, int key, int min, int max){
            long t0 = System.nanoTime();

            int mid = min + ((max - min)/2);

            if (arr[mid] == key){
                long t1 = System.nanoTime();
                System.out.println("The key exists in the array");
                return (t1-t0);
            }
            if((arr[mid] > key) && (min < mid)){
                max = mid - 1;
                return recursive(arr, key, min, max);

            }
            if ((arr[mid] < key) && (mid < max)){
                min = mid + 1;
                return recursive(arr, key, min, max);
            }

            System.out.println("The key does not exist in the array");
            long t1 = System.nanoTime();
            return (t1-t0);
        }

    public static void main(String[] args) throws Exception {
        //int[] array = sorted(64000000);
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int key = 200;
        int rounds = 10;

        long min = Long.MAX_VALUE;
        long max = 0;
        long total = 0;

        for (int i = 0; i < rounds; i++){
            long t = recursive(array, key, 0, (array.length - 1));
            if (t > max) max = t;
            if (t < min) min = t;
            total += t;
        }
        System.out.println(" avg: " + ((double) total)/rounds);
        System.out.println(" min: " + ((double) min));
        System.out.println(" max: " + ((double) max));        
    }
}
