#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// define what components a stack should have 
typedef struct {
    int top;
    int size; 
    int* array;
} Stack ;

// create a function that creates a stack 
Stack* new_stack(int size) {
    int *arr = (int*) malloc(sizeof(int)*size);
    Stack *stk = (Stack*) malloc(sizeof(Stack));
    stk->array = arr;
    stk->size = size;
    stk->top = -1;
    return stk;
} 

void push(Stack* stk, int val) {
    // increase the size of the stack if it is full 
    if (stk->top == stk->size - 1) {
        int size = stk->size * 2;
        int* copy = (int*)malloc(sizeof(int)*size);
        for (int i = 0; i < stk->size; i++) {
            copy[i] = stk->array[i];
        }
        stk->size = size;
        free(stk->array);
        stk->array = copy;
    }
    // increment the top 
    stk->top = stk->top + 1;
    // add the value to the array 
    stk->array[stk->top] =  val;
}

int pop(Stack* stk) {
    int value = 0;
    if (stk->top == -1) {
        printf("The stack is empty. Cannot POP.");
        return value;
    }
    value = stk->array[stk->top];
    stk->array[stk->top] = 0; 
    stk->top = stk->top - 1;
    return value;
}



int main() {
    int n = 10;
    Stack* stack = new_stack(n);

    printf("HP-35 pocket calculator\n");

    char* buffer = malloc(n);

    int run = 1;

    while (run) {
        int val;
        printf(" > ");
        fgets(buffer, n, stdin);
        if (strcmp(buffer, "\n") == 0) {
            run = 0;
        } else if (strcmp(buffer, "+\n") == 0) {
            val = pop(stack) + pop(stack);
            push(stack, val);
        } else if (strcmp(buffer, "-\n") == 0) {
            int temp = pop(stack);
            int temp1 = pop(stack);
            val = temp1 - temp;
            push(stack, val);
        } else if (strcmp(buffer, "*\n") == 0) {
            val = pop(stack) * pop(stack);
            push(stack, val);
        } else {
            val = atoi(buffer);
            push(stack, val);
        }
    }
    printf("the result is: %d\n\n", pop(stack));
    free(stack);
    free(buffer);
    exit(0);
}
