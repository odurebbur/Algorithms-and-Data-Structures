#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "stack.h"

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

    free_stack(stack);
    exit(0);
}
