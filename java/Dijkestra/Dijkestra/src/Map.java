import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Map {

    Path[] done = new Path[135];
    Queue priorityQueue = new Queue();
    City[] cities = new City[135];
    int cityCount = 0;

    public Map(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] row = line.split(",");
                String name = row[0];
                String neighborName = row[1];
                Integer distance = Integer.parseInt(row[2]);
                
                City one = lookup(name);
                City two = lookup(neighborName);

                one.connect(two, distance);
                two.connect(one, distance);
            }
        } catch (Exception e){
            System.out.println("file not found");
        }
    }
    public City lookup(String name){
        for (int i = 0; i < cityCount; i++){
            if (cities[i].name.equals(name)){
                return cities[i];
            }
        }
        if (cityCount < 135){
            City newCity = new City(name, cityCount);
            cities[cityCount++] = newCity;
            return newCity;
        }
        return null;
}

    public class City {
        String name;
        Integer id;
        ConnectionList neighbors;

        public City(String name, Integer id){
            this.name = name;
            neighbors = new ConnectionList();
            this.id = id;
        }
        public void connect(City nxt, int dst){
            neighbors.add(new Connection(nxt, dst));
        }
    }

    public class Path {
        private City city;
        private Path prev;
        Integer dist;

        private Path(City city, Path prev, Integer dist){
            this.city = city;
            this.prev = prev;
            this.dist = dist;
        }
    }

    public class Connection {
        City city;
        Integer distance;

        public Connection(City city, Integer distance){
            this.city = city;
            this.distance = distance;
        }

    }

    public class ConnectionList{
        Node head;

        public ConnectionList(){
            this.head = null;
        }
        public void add(Connection connection){
            Node newNode = new Node(connection);
            if (head == null){
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
        public int length(){
            Node current = head;
            int i = 0;
            while (current.next != null){
                i++;
                current = current.next;
            }
            return i;
        }
    }
    
    private class Node {
        Connection connection;
        Node next;

        public Node(Connection connection){
            this.connection = connection;
            this.next = null;
        }
    }
    public class NodeQ {
        Path path;
        NodeQ next;

        private NodeQ(Path pth, NodeQ nxt){
            this.path = pth;
            this.next = nxt;
        }
    }

    public class Queue {

        NodeQ head;
    
        public void enqueue(Path pth){
            NodeQ newNode = new NodeQ(pth, null);

            if (head == null || pth.dist < head.path.dist){
                newNode.next = head;
                head = newNode;
            } else {
                NodeQ current = head;
                while (current.next != null && current.next.path.dist < pth.dist){
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
            
            }

        public Path dequeue(){
            if (head == null){
                return null;
            }
            Path pth = head.path;
            head = head.next;
            return pth;
            }  
        public boolean isEmpty() {
            if (head == null){
                return true;
            }
            return false;
        }
        }

    private Integer shortest(City from, City to){

        //place first city in the queue with distance 0
        Path intialPath = new Path(from, null, 0);

        priorityQueue.enqueue(intialPath);

        while (!priorityQueue.isEmpty()){
            Path currentPath = priorityQueue.dequeue();
            
            // return the distance if we reached the destination city 
            if (currentPath.city == to){
                return currentPath.dist;
            }
            // mark the current city as visited 
            done[currentPath.city.id] = currentPath;
            
            // explore neighbors
            Node neighborNode = currentPath.city.neighbors.head;
            while (neighborNode != null){
                Connection connection = neighborNode.connection;
                City neighborCity = connection.city;
                int newDist = currentPath.dist + connection.distance;

                //check if shorter with neighbors
                if (done[neighborCity.id] == null || newDist < done[neighborCity.id].dist){
                    Path newPath = new Path(neighborCity, currentPath, newDist);
                    priorityQueue.enqueue(newPath);;
                    done[neighborCity.id] = newPath;
                }
                neighborNode = neighborNode.next;
            }
        } 
        return -1;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println("hello");
        Map map = new Map("europe.csv");

        String from = "Malmö";

        City cityFrom = map.lookup(from);

        String[] destinations = {
            "Dijon", "Basel", "Paris", "Luxenburg", "Verona", "Hamburg",
            "Budapest", "Trondheim", "Florens","Zaragoza", "Metz", "Frankfurt"};
        
        for (int i = 0; i < destinations.length; i++){
            String to = destinations[i];
            City cityTo = map.lookup(to);

            Arrays.fill(map.done, null);

            long t0 = System.nanoTime();
            Integer dist = map.shortest(cityFrom, cityTo);
            long time = (System.nanoTime() - t0) / 1000 ;     
            
            int doneSize = 0;
            for(int j = 0; j < map.done.length; j++){
                if(map.done[j] != null){
                    doneSize++;
                }
            }

            System.out.println("Shortest: " + dist + " min (" + time + " microseconds). Done size = " 
            + doneSize);
            }
    }
}
