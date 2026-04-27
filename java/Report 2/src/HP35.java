import java.io.*;

public class HP35{
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new DynamicStack<Integer>();    
        int sum = 0;

        System.out.println("HP-35 pocket calculator");

        boolean run = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  

        while(run){
            System.out.println(" > ");
            String input = br.readLine();
            switch (input){
                case "+":
                    sum = (int) stack.pop();
                    sum = sum + stack.pop();
                    stack.push(sum);
                    break;

                case "-":
                    sum = stack.pop();
                    sum = sum - stack.pop();
                    stack.push(sum);
                    break;

                case "*":
                    sum = stack.pop();
                    sum = sum * stack.pop();
                    stack.push(sum);
                    break;
                    
                case "":
                    run = false;
                    break;

                default:
                    int i = Integer.parseInt(input);
                    stack.push(i);
                    break;
             }
            }

            System.out.printf("the result is: %d\n\n", stack.pop());
            System.out.printf("I love reversed polish notation, don't you?\n");
        }
}