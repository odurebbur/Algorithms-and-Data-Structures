#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "stack.h"

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

void free_stack(Stack *stk) {
    free(stk->array);
    free(stk);
}
