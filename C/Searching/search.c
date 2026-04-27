#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#include "search.h"

int unsorted_search(int array[], int length, int key) {
    for (int i = 0; i < length; i++) {
        if (array[i] == key) {
            printf("The key is at index %d", i);
            return 1;
        }
    }
    return 0;
}

int* create_array(int size) {
    srand(time(NULL));
    int *arr = (int*) malloc(sizeof(int)*size);
    for (int i = 0; i < size; i++) {
        arr[i] = rand() % 10;
    }
    return arr;
}

void print_array(int* arr, int size) {
    printf("[");
    for (int i = 0; i < size - 1; i++) {
        printf("%d, ", arr[i]);
    }
    printf("%d]\n", arr[size-1]);
}

int* create_sorted_array(int size) {
    srand(time(NULL));
    int next = 0;
    int *array = (int*)malloc(sizeof(int)*size);
    for (int i = 0; i < size; i++) {
        next = next + rand() % 5;
        array[i] = next;
    }
    return array;
}

int binary_search(int *arr, int size, int key) {
    int start_index = 0;
    int end_index = size - 1;
    while (start_index <= end_index) {
        int index = start_index + (end_index - start_index) / 2;
        if (arr[index] == key) {
            printf("key found at index %d\n", index);
            return 1;
        } 
        if (key < arr[index] && index > start_index){
            end_index = index - 1;
            continue;
        }
        if (key > arr[index] && index < end_index) {
            start_index = index + 1;
            continue;
        }
        break;
    }
    return 0;
}

int recursive_search(int *arr, int size, int key, int first, int last) {
    // jump to middle 
    while (first <= last) {
        int index = first + (last - first) / 2;
        if (key == arr[index]) {
            printf("key found at index %d\n", index);
            return 1;
        }
        if (key < arr[index]) {
            return recursive_search(arr, size, key, first, index - 1);
        }
        if (key > arr[index]) {
            return recursive_search(arr, size, key, index + 1, last);
        }
        break;
    }
    return 0;
}
