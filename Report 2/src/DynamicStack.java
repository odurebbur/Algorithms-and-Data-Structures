public class DynamicStack<T> extends Stack<T> {
    T[] stack;
    int top;
    int size = 15;

    public DynamicStack(){
        this.size = size;
        stack = (T[]) new Object[size];
        top = 0;
    }

    public  void push(T val){
        if (top == size){
            T[] copy = (T[]) new Object[size*2];
            for(int i = 0; i < stack.length; i++){
                copy[i] = stack[i];
            }
            stack = copy;
        }
        stack[top++] = val;
    }
    public T pop(){
        top--;
        return stack[top];
    }

    public static void main(String[] args) throws Exception {
        

    }

}
