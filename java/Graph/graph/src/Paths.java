public class Paths {
    Map.City[] path;
    int sp;

    public Paths() {
        path = new Map.City[54];
        sp = 0;
    }

    private Integer shortest(Map.City from, Map.City to, Integer max){
        if(from == to) // reached destination city 
            return 0;
        Integer shrt = null;

        // checks if from has already been visitied in the path 
        for (int i = 0; i < sp; i++){
            if (path[i] == from)
                return null;
        }
        // adds the city to the path to mark it as visited
        path[sp++] = from;

        Map.Node current = from.neighbors.head;

        //check each connection to the from city 
        while (current != null){
            Map.Connection conn = current.connection;
            Map.City neighbor = conn.city;
            int distance = conn.distance;

            //skip paths that are already past the maximum 
            if (max != null && distance >= max){
                current = current.next;
                continue;
            }

            Integer result;

            if (shrt == null){ // no path to the destination has been found yet, so there is no max
                result = shortest(neighbor, to, null);
            } else { //if a shortest path has been found, update the max so that we do not have to go through longer paths
               Integer newMax;
                if(max == null){ // a path has been found but max still have no value 
                    newMax = shrt; 
                } else {
                    newMax =  Math.min(max-distance, shrt); // update newMax with the shorter of the two distances
                }
                result = shortest(neighbor, to, newMax);
            }

            if (result != null){
                int totalDistance = result + distance ;
                if (shrt == null || totalDistance < shrt){
                    shrt = totalDistance;
                }
            }
            current = current.next;
        }

        // backtracks to remove the current city from the path 
        path[sp--] = null;

        return shrt;
    }

    public static void main(String[] args) {

        Map map = new Map("trains.csv");
        Paths path = new Paths();
        String from = "Malmö";
        String to = "Kiruna";
        Integer max = null;

        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0) / 1000000;

        System.out.println("Shortest: " + dist + " min (" + time + " ms)");

    }
    
}
