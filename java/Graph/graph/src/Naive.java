public class Naive {

    private static Integer shortest(Map.City from, Map.City to, Integer max){
        if (max < 0) // exceeded the allowed distance
            return null;
        if(from == to) // reached destination city 
            return 0;

        Integer shrt = null;

        Map.Node current = from.neighbors.head;

        while (current != null){
            Map.Connection conn = current.connection;
            Map.City neighbor = conn.city;
            int distance = conn.distance;


            Integer result = shortest(neighbor, to, max - distance);


            if (result != null){
                int totalDistance = result + distance ;
                if (shrt == null || totalDistance < shrt){
                    shrt = totalDistance;
                }
            }
            current = current.next;
        }
        return shrt;
    }

    public static void main(String[] args) {

        Map map = new Map("trains.csv");
        String from = "Malmö";
        String to = "Stockholm";
        Integer max = Integer.valueOf(500);

        long t0 = System.nanoTime();
        Integer dist = Naive.shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0) / 1000000;

        System.out.println("Shortest: " + dist + " min (" + time + " ms)");

    }
}