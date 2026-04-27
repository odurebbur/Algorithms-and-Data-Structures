#include "sort.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc != 2){
        printf("Incorrect number of args");
        exit(1); // unsuccesful exit 
    } 
    int key = 3;
    int array_size = atoi(argv[1]);
    int *array = create_sorted_array(array_size);
    print_array(array, array_size);
    int found = recursive_search(array, array_size, key, 0, array_size - 1);
    if(!found) {
        printf("Key not found.\n");
    }
    exit(0);
}