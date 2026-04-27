import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class T9 {

    private class Node {
            public Node[] next;
            public boolean valid;

            public Node() {
                next = new Node[27];
                valid = false;
            }
    }
    //given a character, returns a code
    private static int code(char w){
        switch (w){
            case 'a' :
                return 0;
            case 'b' :
                return 1;
            case 'c' :
                return 2;
            case 'd' :
                return 3;
            case 'e' :
                return 4;
            case 'f' :
                return 5;
            case 'g' :
                return 6;
            case 'h' :
                return 7;
            case 'i' :
                return 8;
            case 'j' :
                return 9;
            case 'k' :
                return 10;
            case 'l' :
                return 11;
            case 'm' :
                return 12;
            case 'n' :
                return 13;
            case 'o' :
                return 14;
            case 'p' :
                return 15;
            case 'r' :
                return 16;
            case 's' :
                return 17;
            case 't' :
                return 18;
            case 'u' :
                return 19;
            case 'v' :
                return 20;
            case 'x' :
                return 21;
            case 'y' :
                return 22;
            case 'z' :
                return 23;
            case 'å' :
                return 24;
            case 'ä' :
                return 25;
            case 'ö' :
                return 26;     
        }
        return -1;
    }

    //given a code, returns the character 
    private static char character(int code){
        switch (code){
            case 0 :
                return 'a';
            case 1 :
                return 'b';
            case 2 :
                return 'c';
            case 3 :
                return 'd';
            case 4 :
                return 'e';
            case 5 :
                return 'f';
            case 6 :
                return 'g';
            case 7 :
                return 'h';
            case 8 :
                return 'i';
            case 9 :
                return 'j';
            case 10 :
                return 'k';
            case 11 :
                return 'l';
            case 12 :
                return 'm';
            case 13 :
                return 'n';
            case 14 :
                return 'o';
            case 15 :
                return 'p';
            case 16 :
                return 'r';
            case 17 :
                return 's';
            case 18 :
                return 't';
            case 19 :
                return 'u';
            case 20 :
                return 'v';
            case 21 :
                return 'x';
            case 22 :
                return 'y';
            case 23 :
                return 'z';
            case 24 :
                return 'å';  
            case 25 :
                return 'ä';
            case 26 :
                return 'ö';   
        }
        return (char)-1;
    }

    //given a key returns an index
    private static int index(char key){
        switch(key){
            case '1':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            case '9': 
                return 8;
        }
        return -1;
    }

    //given a character, returns a key 
    private static char key(char character){
        switch (character){
            case 'a' :
                return '1';
            case 'b' :
                return '1';
            case 'c' :
                return '1';
            case 'd' :
                return '2';
            case 'e' :
                return '2';
            case 'f' :
                return '2';
            case 'g' :
                return '3';
            case 'h' :
                return '3';
            case 'i' :
                return '3';
            case 'j' :
                return '4';
            case 'k' :
                return '4';
            case 'l' :
                return '4';
            case 'm' :
                return '5';
            case 'n' :
                return '5';
            case 'o' :
                return '5';
            case 'p' :
                return '6';
            case 'r' :
                return '6';
            case 's' :
                return '6';
            case 't' :
                return '7';
            case 'u' :
                return '7';
            case 'v' :
                return '7';
            case 'x' :
                return '8';
            case 'y' :
                return '8';
            case 'z' :
                return '8';
            case 'å' :
                return '9';
            case 'ä' :
                return '9';
            case 'ö' :
                return '9';     
        }
        return (char) -1;
    }

    private static char[] character(char key){
        switch(key){
            case '1':
                return new char[] {'a', 'b', 'c'};
            case '2':
                return new char[] {'d', 'e', 'f'};
            case '3':
                return new char[] {'g', 'h', 'i'};
            case '4':
                return new char[] {'j', 'k', 'l'};
            case '5':
                return new char[] {'m', 'n', 'o'};
            case '6':
                return new char[] {'p', 'r', 's'};
            case '7':
                return new char[] {'t', 'u', 'v'};
            case '8':
                return new char[] {'x', 'y', 'z'};
            case '9':
                return new char[] {'å', 'ä', 'ö'};
        }
        return new char[] {};
    }
    
    public char[] stringToArray(String string){
        char[] ch = new char[string.length()];

        for(int i = 0; i < string.length(); i++){
            ch[i] = string.charAt(i);
        }
        return ch;
    }
    
    void add(String word){
        // convert the word into an array of characters
        Node current = root;

        char[] ch = new char[word.length()];

        for(int i = 0; i < word.length(); i++){
            ch[i] = word.charAt(i);
            int code = code(ch[i]);
            if(current.next[code] == null){
                current.next[code] = new Node();
            }
            current = current.next[code];
        }
        current.valid = true;
    }
    // return all possible words
    ArrayList<String> decode(String decodeMe){
        ArrayList<String> result = new ArrayList<>();
        collect(root, decodeMe, 0, "", result);
        return result;
    }

    private void collect(Node node, String decodeMe, int index, String route, ArrayList<String> result){
        if(index == decodeMe.length()){ // if we have decoded the whole thing
            if (node.valid == true){
                result.add(route); // add valid word to the array list 
            }
            return;
        }
        // get the array of what the letters are at that index
        char key = decodeMe.charAt(index);
        char[] possibleLetters = character(key);

        // go through each letter and explore those options
        for(int i = 0; i < possibleLetters.length; i++){
            //convert the letter into the code
            int code = code(possibleLetters[i]);
            if(node.next[code] != null){// check if it is empty
                collect(node.next[code], decodeMe, index + 1, route + possibleLetters[i], result);//if it is not empty, then explore this branch 
            }
        }
    }

    private Node root;
    
    public T9(){
        root = new Node();
    }

    public static void main(String[] args) {
        T9 tree = new T9();

        
        try (BufferedReader br = new BufferedReader(new FileReader("kelly.txt"))){
            String line;
            while((line = br.readLine()) != null){
                tree.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords = tree.decode("15261");

        for(int i = 0; i < listOfWords.size(); i++){
            System.out.println(listOfWords.get(i));
        }

    }
    
}
