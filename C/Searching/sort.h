#ifndef SORT_H
#define SORT_H


int* create_array(int size);
int unsorted_search(int array[], int length, int key);
void print_array(int* arr, int size);
int* create_sorted_array(int size);
int binary_search(int *arr, int size, int key);
int recursive_search(int *arr, int size, int key, int first, int last);

#endif