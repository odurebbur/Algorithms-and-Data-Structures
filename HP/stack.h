#ifndef STACK_H
#define STACK_H

typedef struct {
    int top;
    int size;
    int *array;
} Stack;

Stack *new_stack(int size);
void push(Stack *stk, int val);
int pop(Stack *stk);

#endif
