import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Map {
    private City[] cities;
    private final int mod = 103; // add here 

    public Map(String file){
        cities = new City[mod]; //serves as hash table 

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
            int index = hash(name, mod);
            while (cities[index] != null){
                if (cities[index].name.equals(name)){
                    return cities[index];
                }
                index = (index + 1) % mod;
            }
            //end up here if city is not found
            City newCity = new City(name);
            cities[index] = newCity;
            return newCity;
    }

    public class City {
        String name;
        ConnectionList neighbors;

        public City(String name){
            this.name = name;
            this.neighbors = new ConnectionList();
        }

        public void connect(City nxt, int dst){
            neighbors.add(new Connection(nxt, dst));
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

    public class Node {
        Connection connection;
        Node next;

        public Node(Connection connection){
            this.connection = connection;
            this.next = null;
        }
    }

    // create a class that is a linked list of connections
    public class ConnectionList{
        Node head;

        //constructor 
        public ConnectionList(){
            this.head = null;
        }
        // add a connection to the linked list 
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
                while(current != null){
                    current = current.next;
                    i++;
                }
                return i;
            }
        }

    private static Integer hash(String name, int mod){
        int hash = 0;
        for (int i = 0; i < name.length(); i++){
            hash = (hash*31 +name.charAt(i)) % mod;
        }
        return hash;
    }

    public static void main(String[] args) {


    }
    
    }


